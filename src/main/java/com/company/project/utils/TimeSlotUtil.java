package com.company.project.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间段公共方法
 */
public class TimeSlotUtil {

	// 日志器
	private static final Logger logger = Logger.getLogger(TimeSlotUtil.class);

	// public static SimpleDateFormat SHORT_SDF = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss");

	//年月日
	static String strDateFormat = "yyyy-MM-dd";
	static SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

	/**
	 * 生成指定时间
	 * @param args
	 */
	public static void main(String[] args ){

		for (int i = 7; i < 19; i ++){
			if (i < 10){
				System.out.println(sdf.format(new Date())  + " 0" + i + ":00:00");
			}else {
				System.out.println(sdf.format(new Date())  + " " + i + ":00:00");
			}
		}
	}

	/**
	 * 生成全天
	 * @param args
	 */
	public static void main1(String[] args){

		for (int i = 0; i < 24; i ++){
			if (i < 10){
				System.out.println(sdf.format(new Date())  + " 0" + i + ":00:00");
			}else {
				System.out.println(sdf.format(new Date())  + " " + i + ":00:00");
			}
		}
	}

}
