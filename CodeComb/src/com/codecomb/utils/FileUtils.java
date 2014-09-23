package com.codecomb.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.content.Context;
import android.content.res.AssetManager;

public class FileUtils {

	public static String readFile(File file) {
		String result = null;

		FileInputStream in = null;
		try {

			in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			StringBuilder sbContent = new StringBuilder();
			String temp;
			while ((temp = reader.readLine()) != null) {
				sbContent.append(temp);
			}

			result = sbContent.toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;

	}

	public static String readFile(String filePath) {
		String content = null;

		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				content = readFile(file);
			}
		}

		return content;
	}

	public static byte[] readBytes(File file) throws IOException {
		byte[] bytes = null;
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			bytes = new byte[in.available()];
			in.read(bytes);

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return bytes;
	}

	public static byte[] readBytes(String filePath) throws IOException {

		return readBytes(new File(filePath));
	}

	public static void writeBytes(File file, byte[] bytes) throws IOException {

		OutputStream out = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(bytes);

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void writeBytes(String filePath, byte[] bytes)
			throws IOException {
		writeBytes(new File(filePath), bytes);
	}

	public static void writeFile(File file, String content) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(file));
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writer.write(content);
		writer.close();
		out.close();
	}

	public static void copyFile(File src, File dest) throws IOException {

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				src));
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));

		int c;
		byte[] buffer = new byte[1024];
		while ((c = in.read(buffer)) != -1) {
			for (int i = 0; i < c; i++) {
				out.write(buffer[i]);
			}
		}

		in.close();
		out.close();

	}

	public static void copyAssetFile(Context context, String src, File des)
			throws Exception {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			AssetManager am = context.getAssets();
			inputStream = am.open(src);

			outputStream = new FileOutputStream(des);
			byte buffer[] = new byte[1024];
			int length = 0;

			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}

	public static void Unzip(File zipFile, String descDir) throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*",
					"/");
			// �ж�·���Ƿ����,�������򴴽��ļ�·��
			String s = outPath.substring(0, outPath.lastIndexOf('/'));
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// �ж��ļ�ȫ·���Ƿ�Ϊ�ļ���,����������Ѿ��ϴ�,����Ҫ��ѹ
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// ����ļ�·����Ϣ
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
	}

	public static String getMIMEType(String url) {

		String type = "*/*";

		// ��ȡ��׺��ǰ�ķָ���"."��fName�е�λ�á�
		int dotIndex = url.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* ��ȡ�ļ��ĺ�׺�� */
		String end = url.substring(dotIndex, url.length()).toLowerCase();
		if (end != "") {
			// ��MIME���ļ����͵�ƥ������ҵ���Ӧ��MIME���͡�
			for (int i = 0; i < MIME_MapTable.length; i++) {
				if (end.equals(MIME_MapTable[i][0])) {
					type = MIME_MapTable[i][1];
					break;
				}
			}
		}

		return type;
	}

	public static final String[][] MIME_MapTable = {
			// {��׺����MIME����}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/mp3" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

}
