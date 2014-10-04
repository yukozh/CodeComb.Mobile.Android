
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午3:29:23
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.infrastructure.wabapi.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.util.Log;

import com.codecomb.exceptions.AppException;
import com.codecomb.utils.Utils;
import com.google.gson.GsonBuilder;



public class HttpNetClient implements NetClient {

	private static final String TAG = HttpNetClient.class.getSimpleName();

	private  static String appUserAgent;
	
	public static final int MAX_RETRY_TIMES = 0;
	public static final int INTERVAL_RETRY = 1 * 1000;

	public static final int TIMEOUT_SOCKET = 60 * 1000;
	public static final int TIMEOUT_CONNECTION = 60 * 1000;
	public static final String CONTENT_CHARSET = "UTF-8";

	public static final String CONTENT_TYPE = "application/json";
	
	
	@Override
	public String doGet(Context context, String baseUrl, Map<String, Object> params)
			throws AppException {
		String url = createGetUrl(baseUrl, params);
		String cookie = getCookie(context);
		String userAgent = getUserAgent(context);

		StringBuilder buffer = null;
		int times = 0;
		do {

			InputStream in = null;
			BufferedReader reader = null;
			try {
				HttpClient httpClient = createHttpClient();
				HttpGet httpGet = createHttpGet(url, cookie, userAgent);

				HttpResponse rsp;
				try {
					rsp = httpClient.execute(httpGet);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					throw AppException.http(e);

				}

				if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					HttpEntity entity = rsp.getEntity();

					in = entity.getContent();
					reader = new BufferedReader(new InputStreamReader(in));

					buffer = new StringBuilder();
					String readline = "";
					while ((readline = reader.readLine()) != null) {
						buffer.append(readline);
					}

					break;

				} else {

					throw AppException.http(rsp.getStatusLine().getStatusCode()
							+ "");

				}
			} catch (IOException e) {

				times++;
				if (times < MAX_RETRY_TIMES) {
					try {
						Thread.sleep(INTERVAL_RETRY);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					throw AppException.network(e);
				}
			} finally {

				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		} while (times < MAX_RETRY_TIMES);

		return buffer.toString();
	}

	@Override
	public String doPost(Context context, String url, Map<String, Object> params)
			throws AppException {
		// String cookie = getCookie(context);
				// String userAgent = getUserAgent(context);

				StringBuilder buffer = null;
				int times = 0;
				do {

					InputStream in = null;
					BufferedReader reader = null;
					try {

						HttpClient httpClient = createHttpClient();

						// HttpPost httpPost = createHttpPost(url, cookie, userAgent);

						HttpPost httpPost = createHttpPost(url);

						httpPost.setEntity(createEntity(params));

						HttpResponse rsp = null;
						try {

							Log.e(TAG, "请求数据");

							rsp = httpClient.execute(httpPost);

						} catch (ClientProtocolException e) {
							Log.e(TAG, "ClientProtocolException :", e);
							throw AppException.http(e);

						}

						if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

							HttpEntity entity = rsp.getEntity();

							in = entity.getContent();
							reader = new BufferedReader(new InputStreamReader(in));

							buffer = new StringBuilder();
							String readline = "";
							while ((readline = reader.readLine()) != null) {
								buffer.append(readline);
							}

							break;

						} else {

							// 请求失败，不可修复，直接退出循环，抛出异常。
							throw AppException.http(rsp.getStatusLine().getStatusCode()
									+ "");

						}

					} catch (IOException e) {

						times++;
						if (times < Constants.MAX_RETRY_TIMES) {
							try {
								Thread.sleep(Constants.INTERVAL_RETRY);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							throw AppException.network(e);
						}
					} finally {

						if (in != null) {
							try {
								in.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (reader != null) {
							try {
								reader.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

				} while (times < Constants.MAX_RETRY_TIMES);

				return buffer.toString();
	}

	@Override
	public String doPostMultipart(Context context, String url,
			Map<String, Object> params, List<String> files) throws AppException {

		StringBuilder buffer = null;
		int times = 0;
		do {

			InputStream in = null;
			BufferedReader reader = null;
			try {

				HttpClient httpClient = createHttpClient();

				HttpPost httpPost = new HttpPost(url);

				MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
						.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
						.setCharset(Charset.forName(HTTP.UTF_8));

				if (params != null) {
					for (String k : params.keySet()) {

						multipartEntityBuilder.addTextBody(k, params.get(k)
								.toString(), ContentType.create("text/plain",
								Charset.forName(HTTP.UTF_8)));

						// (
						// k,
						// new StringBody(params.get(k), ContentType
						// .create("text/plain",
						// Charset.forName(HTTP.UTF_8))));
					}
				}

				if (files != null) {

					for (String f : files) {

						File file = new File(f);
						multipartEntityBuilder.addBinaryBody(file.getName(),
								file);

					}
				}

				httpPost.setEntity(multipartEntityBuilder.build());

				HttpResponse rsp = null;
				try {
					rsp = httpClient.execute(httpPost);

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					throw AppException.http(e);

				}

				if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					HttpEntity entity = rsp.getEntity();

					in = entity.getContent();
					reader = new BufferedReader(new InputStreamReader(in));

					buffer = new StringBuilder();
					String readline = "";
					while ((readline = reader.readLine()) != null) {
						buffer.append(readline);
					}

					break;

				} else {

					// 请求失败，不可修复，直接退出循环，抛出异常。
					throw AppException.http(rsp.getStatusLine().getStatusCode()
							+ "");

				}

			} catch (IOException e) {

				times++;
				if (times < MAX_RETRY_TIMES) {
					try {
						Thread.sleep(INTERVAL_RETRY);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					throw AppException.network(e);
				}
			} finally {

				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		} while (times < MAX_RETRY_TIMES);

		return buffer.toString();
	}
	
	
	private static String getCookie(Context context){
		
		return null;
	}
	
	
	private String getUserAgent(Context context){
		
		if (appUserAgent == null || appUserAgent == "") {

			StringBuilder ua = new StringBuilder("Include");
			ua.append('/' + Utils.getVersionName(context) + '_'
					+ Utils.getVersionCode(context));
			ua.append("/Android");
			ua.append("/" + android.os.Build.VERSION.RELEASE);
			ua.append("/" + android.os.Build.MODEL); 
			ua.append("/" + Utils.getDeviceId(context));
			ua.append("/" + Utils.getVersionCode(context));

			appUserAgent = ua.toString();
		}
		return appUserAgent;
		
	}
	
	
	private static HttpClient createHttpClient() {

		HttpClient httpClient = new DefaultHttpClient();

		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_CONNECTION);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				TIMEOUT_SOCKET);
		httpClient.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET, CONTENT_CHARSET);

		return httpClient;
	}
	
	

	private static String createGetUrl(String baseUrl,
			Map<String, Object> params) {
		StringBuilder builder = new StringBuilder(baseUrl);

		if (params != null) {
			builder.append("?");

			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				if (value != null) {
					builder.append(key + "=" + value + "&");
				}
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}
	

	private static HttpGet createHttpGet(String url, String cookie,
			String userAgent) {
		HttpGet httpGet = new HttpGet(url);

		httpGet.setHeader("Cookie", cookie);
		httpGet.setHeader("User-Agent", userAgent);
		httpGet.setHeader("Connection", "Keep-Alive");

		return httpGet;
	}
	
	private static HttpPost createHttpPost(String url) {

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Connection", "Keep-Alive");
		return httpPost;
	}
	
	

	
	private static HttpPost createHttpPost(String url, String cookie,
			String userAgent) {
		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader("Cookie", cookie);
		httpPost.setHeader("User-Agent", userAgent);
		httpPost.setHeader("Connection", "Keep-Alive");

		return httpPost;
	}
	
	private static StringEntity createEntity(Map<String, Object> params)
			throws AppException {
		if (params == null) {
			params = new HashMap<String, Object>();
		}

		StringEntity entity;
		try {

			String json = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create()
					.toJson(params);

			entity = new StringEntity(json, "UTF-8");
			entity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw AppException.parse(e);
		}

		return entity;
	}
	
	
	
	
	
	

}

