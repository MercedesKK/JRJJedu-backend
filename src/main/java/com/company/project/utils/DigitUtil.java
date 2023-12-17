package com.company.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Random;

public class DigitUtil {
	// 日志器
	private static final Logger logger = Logger.getLogger(DigitUtil.class);
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 生成long类型ID
	 * 
	 * @return
	 */
	public static long generatorLongId() {
		return IdWorker.getInstance().nextId();
	}

	public static Integer parseToInt(String str) {
		return parseToInt(str, 0);
	}

	public static Integer parseToInt(String str, Integer defaultValue) {
		if (StringUtils.isEmpty(str)) {
			return defaultValue;
		}

		Integer result = defaultValue;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public static Long parseToLong(String str) {
		return parseToLong(str, 0L);
	}

	public static Long parseToLong(String str, Long defaultValue) {
		if (StringUtils.isEmpty(str)) {
			return defaultValue;
		}

		Long result = defaultValue;
		try {
			result = Long.parseLong(str);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public static Float parseToFloat(String str) {
		return parseToFloat(str, 0.0f);
	}

	public static Float parseToFloat(String str, Float defaultValue) {
		if (StringUtils.isEmpty(str)) {
			return defaultValue;
		}

		Float result = defaultValue;
		try {
			result = Float.parseFloat(str);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	public static Double parseToDouble(String str) {
		return parseToDouble(str, 0.0d);
	}

	public static Double parseToDouble(String str, Double defaultValue) {
		if (StringUtils.isEmpty(str)) {
			return defaultValue;
		}

		Double result = defaultValue;
		try {
			result = Double.parseDouble(str);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	public static double add(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.add(b2).doubleValue(), scale);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	public static float mul(float v1, float v2, int scale) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return round(b1.multiply(b2).floatValue(), scale);
	}

	public static double mul(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.multiply(b2).doubleValue(), scale);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static BigDecimal div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static BigDecimal div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static double divDouble(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static float round(float v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}


	public static String genRandomNum(){
		int  maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < 8){
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count ++;
			}
		}
		return pwd.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100000;i++)
		{
			String s = generatorLongId()+"";
		System.out.println(s+"=="+s.substring(s.length() - 10, s.length()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
