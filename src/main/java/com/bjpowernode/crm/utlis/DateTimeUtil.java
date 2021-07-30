package com.bjpowernode.crm.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	public static final SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdf19 = new SimpleDateFormat("yyyy-MM-dd");

	public static String getSysTimeStr(){
		Date date = new Date();
		String dateStr = sdf19.format(date);
		return dateStr;
		
	}

	public static String getSysTimeForUpload(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateStr = sdf.format(date);
		return dateStr;

	}
}
