package com.barcode;

import android.content.Context;
import android.content.res.Configuration;

public class ActivityUtil {

	/**
	 * 
	 * ���ص�ǰ��Ļ�Ƿ�Ϊ������
	 * 
	 * @param context
	 * 
	 * @return ���ҽ�����ǰ��ĻΪ����ʱ����true,���򷵻�false��
	 */

	public static boolean isScreenOrientationLandscape(Context context) {

		 
		return	context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

	}

}
