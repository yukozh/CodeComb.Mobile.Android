package com.codecomb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertUtils {

	public static float parseSafeFloat(String number, float def) {
		float result = def;

		try {
			result = Float.parseFloat(number);
		} catch (Exception e) {

		}

		return result;
	}

	public static Integer parseSafeInt(String number, Integer def) {
		Integer result = def;

		try {
			result = Integer.parseInt(number);
		} catch (Exception e) {

		}

		return result;
	}

	public static boolean parseSafeBoolean(String number, boolean def) {
		boolean result = def;

		try {
			result = Boolean.parseBoolean(number);
		} catch (Exception e) {

		}

		return result;
	}

	public static Double parseSafeDouble(String num, Double def) {
		// TODO Auto-generated method stub
		Double result = def;

		try {
			result = Double.parseDouble(num);
		} catch (Exception e) {

		}

		return result;
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				Locale.CHINESE);

		return formatter.format(date);
	}

	public static Long parseSafeLong(String number) {
		Long result = null;

		if (number == null) {
			return null;

		}
		try {
			result = Long.parseLong(number);
		} catch (Exception e) {

		}

		return result;
	}

	public static Date parseSafeDate(Long date) {
		Date result = null;
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
		// Locale.CHINESE);

		try {

			if (date == 0) {

				result = null;
			} else {
				Date d = new java.util.Date(date);
				result = d;
			}
			// result = format.parse(date);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public static String toDotNetDateString(Date date) {
		String result = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
					Locale.CHINESE);

			result = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * ??��?�形�?byte??��??
	 * 
	 * @param n
	 *            ??��?�形??��??
	 * @return �???��???????��??
	 */
	public static byte[] longToBytes(long n) {
		byte[] b = new byte[8];
		b[7] = (byte) (n & 0xff);
		b[6] = (byte) (n >> 8 & 0xff);
		b[5] = (byte) (n >> 16 & 0xff);
		b[4] = (byte) (n >> 24 & 0xff);
		b[3] = (byte) (n >> 32 & 0xff);
		b[2] = (byte) (n >> 40 & 0xff);
		b[1] = (byte) (n >> 48 & 0xff);
		b[0] = (byte) (n >> 56 & 0xff);
		return b;
	}

	/**
	 * ??��?�形�?byte??��??
	 * 
	 * @param n
	 *            ??��?�形??��??
	 * @param array
	 *            �???��?????�????
	 * @param offset
	 *            �?�?offset�?�?�?�????
	 */
	public static void longToBytes(long n, byte[] array, int offset) {
		array[7 + offset] = (byte) (n & 0xff);
		array[6 + offset] = (byte) (n >> 8 & 0xff);
		array[5 + offset] = (byte) (n >> 16 & 0xff);
		array[4 + offset] = (byte) (n >> 24 & 0xff);
		array[3 + offset] = (byte) (n >> 32 & 0xff);
		array[2 + offset] = (byte) (n >> 40 & 0xff);
		array[1 + offset] = (byte) (n >> 48 & 0xff);
		array[0 + offset] = (byte) (n >> 56 & 0xff);
	}

	/**
	 * bytes �? Long
	 * 
	 * @param array
	 *            �?�???��??byte
	 * @return long??��?�形??��??
	 */
	public static long bytesToLong(byte[] array) {
		return ((((long) array[0] & 0xff) << 56)
				| (((long) array[1] & 0xff) << 48)
				| (((long) array[2] & 0xff) << 40)
				| (((long) array[3] & 0xff) << 32)
				| (((long) array[4] & 0xff) << 24)
				| (((long) array[5] & 0xff) << 16)
				| (((long) array[6] & 0xff) << 8) | (((long) array[7] & 0xff) << 0));
	}

	/**
	 * byte??��??�???��?�形??��??
	 * 
	 * @param array
	 *            �?�???��??byte??��??
	 * @param offset
	 *            �?�?offset�?�?�????
	 * @return �???��???????��?�形??��??
	 */
	public static long bytesToLong(byte[] array, int offset) {
		return ((((long) array[offset + 0] & 0xff) << 56)
				| (((long) array[offset + 1] & 0xff) << 48)
				| (((long) array[offset + 2] & 0xff) << 40)
				| (((long) array[offset + 3] & 0xff) << 32)
				| (((long) array[offset + 4] & 0xff) << 24)
				| (((long) array[offset + 5] & 0xff) << 16)
				| (((long) array[offset + 6] & 0xff) << 8) | (((long) array[offset + 7] & 0xff) << 0));
	}

	public static byte[] intToBytes(int n) {
		byte[] b = new byte[4];
		// b[3] = (byte) (n & 0xff);
		// b[2] = (byte) (n >> 8 & 0xff);
		// b[1] = (byte) (n >> 16 & 0xff);
		// b[0] = (byte) (n >> 24 & 0xff);

		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);

		return b;
	}

	public static void intToBytes(int n, byte[] array, int offset) {
		array[3 + offset] = (byte) (n & 0xff);
		array[2 + offset] = (byte) (n >> 8 & 0xff);
		array[1 + offset] = (byte) (n >> 16 & 0xff);
		array[offset] = (byte) (n >> 24 & 0xff);
	}

	/**
	 * @param b
	 * @return
	 */
	public static int bytesToInt(byte b[]) {
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
				| (b[0] & 0xff) << 24;
	}

	/**
	 * byte ??��??�? int
	 * 
	 * @param b
	 *            byte??��??
	 * @param offset
	 *            �???��?????�????�?�?�?�?
	 * @return ??�形
	 */
	public static int bytesToInt(byte b[], int offset) {
		return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8
				| (b[offset + 1] & 0xff) << 16 | (b[offset] & 0xff) << 24;
	}

	/**
	 * ???�???��?�形�???��??
	 * 
	 * @param n
	 *            �?�???��????�形
	 * @return byte??��??
	 */
	public static byte[] uintToBytes(long n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);

		return b;
	}

	public static void uintToBytes(long n, byte[] array, int offset) {
		array[3 + offset] = (byte) (n);
		array[2 + offset] = (byte) (n >> 8 & 0xff);
		array[1 + offset] = (byte) (n >> 16 & 0xff);
		array[offset] = (byte) (n >> 24 & 0xff);
	}

	public static long bytesToUint(byte[] array) {
		return ((long) (array[3] & 0xff)) | ((long) (array[2] & 0xff)) << 8
				| ((long) (array[1] & 0xff)) << 16
				| ((long) (array[0] & 0xff)) << 24;
	}

	public static long bytesToUint(byte[] array, int offset) {
		return ((long) (array[offset + 3] & 0xff))
				| ((long) (array[offset + 2] & 0xff)) << 8
				| ((long) (array[offset + 1] & 0xff)) << 16
				| ((long) (array[offset] & 0xff)) << 24;
	}

	public static byte[] shortToBytes(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) ((n >> 8) & 0xff);
		return b;
	}

	public static void shortToBytes(short n, byte[] array, int offset) {
		array[offset + 1] = (byte) (n & 0xff);
		array[offset] = (byte) ((n >> 8) & 0xff);
	}

	public static short bytesToShort(byte[] b) {
		return (short) (b[1] & 0xff | (b[0] & 0xff) << 8);
	}

	public static short bytesToShort(byte[] b, int offset) {
		return (short) (b[offset + 1] & 0xff | (b[offset] & 0xff) << 8);
	}

	public static byte[] ushortToBytes(int n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) ((n >> 8) & 0xff);
		return b;
	}

	public static void ushortToBytes(int n, byte[] array, int offset) {
		array[offset + 1] = (byte) (n & 0xff);
		array[offset] = (byte) ((n >> 8) & 0xff);
	}

	public static int bytesToUshort(byte b[]) {
		return b[1] & 0xff | (b[0] & 0xff) << 8;
	}

	public static int bytesToUshort(byte b[], int offset) {
		return b[offset + 1] & 0xff | (b[offset] & 0xff) << 8;
	}

	public static byte[] ubyteToBytes(int n) {
		byte[] b = new byte[1];
		b[0] = (byte) (n & 0xff);
		return b;
	}

	public static void ubyteToBytes(int n, byte[] array, int offset) {
		array[0] = (byte) (n & 0xff);
	}

	public static int bytesToUbyte(byte[] array) {
		return array[0] & 0xff;
	}

	public static int bytesToUbyte(byte[] array, int offset) {
		return array[offset] & 0xff;
	}
	// char 类�????? float???double 类�????? byte[]
	// ??��??�???��??�???��?�系�????继续???究�????��??

}
