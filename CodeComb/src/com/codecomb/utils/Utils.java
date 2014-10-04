package com.codecomb.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.codecomb.MyApplication;
import com.codecomb.SettingsManager;
import com.codecomb.ufreedom.R;

public class Utils {

	private static final String LOG = "Utils";

	private static final int NETWORK_TYPE_NONE = 0;
	private static final int NETWORK_TYPE_WIFI = 1;
	private static final int NETWORK_TYPE_MOBILE = 2;

	public static String getDeviceSerial() {

		/*
		 * String macAddress = null; WifiManager wifiMgr =
		 * (WifiManager)HelperApplication
		 * .getInstance().getSystemService(Context.WIFI_SERVICE); WifiInfo info
		 * = (null == wifiMgr ? null : wifiMgr.getConnectionInfo()); if (null !=
		 * info) { macAddress = info.getMacAddress();
		 * 
		 * } return macAddress;
		 */

		TelephonyManager tm = (TelephonyManager) MyApplication.getInstance()
				.getApplicationContext()
				.getSystemService(Context.TELEPHONY_SERVICE);

		return tm.getDeviceId();

	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean availableNetwork(Context context) {

		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		return networkInfo != null;

	}

	public static int getNetworkType(Context context) {

		int type = NETWORK_TYPE_NONE;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo == null) {
			return type;
		}

		int nType = networkInfo.getType();

		if (nType == ConnectivityManager.TYPE_WIFI) {
			return NETWORK_TYPE_WIFI;
		} else if (nType == ConnectivityManager.TYPE_MOBILE) {
			return NETWORK_TYPE_MOBILE;
		}

		return type;
	}

	public static void sendSMSInBackground(Context context, String sender,
			String receiver, String content, PendingIntent sentIntent,
			PendingIntent deliveryIntent) {

		if (content != null) {
			SmsManager sms = SmsManager.getDefault();

			// sms.sendTextMessage(receiver, sender, content, sentIntent,
			// deliveryIntent);

			if (content.length() > 70) {

				ArrayList<String> texts = sms.divideMessage(content);

				ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(
						texts.size());
				ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();
				for (int i = 0; i < texts.size(); i++) {
					sentIntents.add(sentIntent);
					deliveryIntents.add(deliveryIntent);
				}

				sms.sendMultipartTextMessage(receiver, sender, texts,
						sentIntents, deliveryIntents);

			} else {

				sms.sendTextMessage(receiver, sender, content, sentIntent,
						deliveryIntent);
			}

		}
	}

	public static String getVersionName(Context context) {

		String versionName = null;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			versionName = info.versionName;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return versionName;
	}

	public static int getVersionCode(Context context) {

		int versionCode = 0;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			versionCode = info.versionCode;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return versionCode;
	}

	public static String getVerName(Context context) {
		String verName = null;

		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			verName = info.versionName;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return verName;

	}

	public static String getDeviceId(Context context) {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		return tm.getDeviceId();

	}
	public static void exit() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public static void callPhone(Context context, String number) {
		Intent phoneIntent = new Intent("android.intent.action.CALL",
				Uri.parse("tel:" + number));
		context.startActivity(phoneIntent);
	}

	public static void sendSMS(Context context, String phoneNumber,
			String message) {

		Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"
				+ phoneNumber));
		intent.putExtra("sms_body", message);
		context.startActivity(intent);

	}

	public static void recordAudio(Activity activity, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr"); // String AUDIO_AMR =
										// "audio/amr";
		intent.setClassName("com.android.soundrecorder",
				"com.android.soundrecorder.SoundRecorder");
		activity.startActivityForResult(intent, requestCode);
	}

	public static void recordVideo(Activity activity, int durationLimit,
			int sizeLimit, int requestCode) {

		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
		intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, sizeLimit);
		intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationLimit);

		activity.startActivityForResult(intent, requestCode);
	}

	public static boolean isNetworkUseable(Context context) {
		// TODO Auto-generated method stub

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		return networkInfo != null && networkInfo.isAvailable();

	}

	public static void hideSoftInputFromWindow(Activity activity) {

		((InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(activity.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

	}

	public static void toggleSoftKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

	}

	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					context.getString(R.string.package_name), 0).versionCode;
		} catch (NameNotFoundException e) {
			Log.e(LOG, e.getMessage());
		}
		return verCode;
	}

	public static String getSystemMetrics(Context context) {
		String sysMetrics = null;

		int screen_w = 0;
		int screen_h = 0;

		int ver = Build.VERSION.SDK_INT;

		DisplayMetrics dm = new DisplayMetrics();
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		display.getMetrics(dm);
		screen_w = dm.widthPixels;
		if (ver < 13) {
			screen_h = dm.heightPixels;
			sysMetrics = screen_w + "_" + screen_h;

		} else if (ver >= 13) {
			String dpi = null;
			@SuppressWarnings("rawtypes")
			Class c;
			try {
				c = Class.forName("android.view.Display");
				@SuppressWarnings("unchecked")
				Method method = c.getMethod("getRealMetrics",
						DisplayMetrics.class);
				method.invoke(display, dm);
				dpi = dm.widthPixels + "_" + dm.heightPixels;
				sysMetrics = dpi;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sysMetrics;
	}

	public static int getSystemVersion(Context context) {

		String s = android.os.Build.VERSION.RELEASE;
		s = s.substring(0, 1);
		int version = ConvertUtils.parseSafeInt(s, -1);

		return version;
	}

	public static double getDeviceInchs(Context context) {

		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // ��Ļ��ȣ����أ�
		int height = metric.heightPixels; // ��Ļ�߶ȣ����أ�
		float density = metric.density; // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5��
		int densityDpi = metric.densityDpi; // ��Ļ�ܶ�DPI��120 / 160 / 240��
		double diagonalPixels = Math.sqrt(Math.pow(width, 2)
				+ Math.pow(height, 2));
		double screenSize = diagonalPixels / (160 * density);

		return screenSize;
	}

	public static File getAppRootDir() throws Exception {
		File appRootDir = null;

		Context context = MyApplication.getInstance().getApplicationContext();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			appRootDir = new File(Environment.getExternalStorageDirectory(),
					context.getResources().getString(R.string.app_name));
		}
		/*
		 * else { appRootDir = context.getFilesDir();
		 * 
		 * }
		 */

		if (!appRootDir.exists()) {
			appRootDir.mkdirs();
		}

		return appRootDir;

	}

	public static File getCurrentUserDir(Context context) throws Exception {
		File appRoot = getAppRootDir();

		File userDir = new File(appRoot, SettingsManager.getInstance()
				.getUsername());
		if (!userDir.exists()) {
			userDir.mkdirs();
		}

		return userDir;
	}

	public static void showToast(Context context, int strResId) {
		Toast.makeText(context, context.getString(strResId), Toast.LENGTH_SHORT)
				.show();
	}

	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	// public static int getVerCode(Context context) {
	// int verCode = -1;
	// try {
	// verCode = context.getPackageManager().getPackageInfo(
	// context.getString(R.string.), 0).versionCode;
	// } catch (NameNotFoundException e) {
	// Log.e(LOG, e.getMessage());
	// }
	// return verCode;
	// }

	// public static String getVerName(Context context) {
	// String verName = context.getResources().getString(R.string.verName);
	//
	// return verName;
	//
	// }

	public static String getAppName(Context context) {
		String verName = context.getResources().getText(R.string.app_name)
				.toString();
		return verName;
	}

	public static void updateApp(Context context, File file) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");

		context.startActivity(intent);
	}

	public static void showImage(Context context, String path) {
		File file = new File(path);
		Intent it = new Intent(Intent.ACTION_VIEW);
		Uri mUri = Uri.parse("file://" + file.getPath());
		it.setDataAndType(mUri, "image/*");
		context.startActivity(it);
	}

	public static void showVideo(Context context, String path) {

		File file = new File(path);
		Intent it = new Intent("com.cooliris.media.MovieView");
		it.setAction(Intent.ACTION_VIEW);
		it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Uri uri = Uri.parse("file://" + file.getPath());
		it.setType("video/mp4");
		it.setDataAndType(uri, "video/mp4");
		context.startActivity(it);
	}
}
