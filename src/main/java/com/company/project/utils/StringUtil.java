package com.company.project.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 驼峰转下划线,效率比上面高
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine(String)})
     * @param str
     * @return
     */
    public static String humpToLine2(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    /**
     * object转String
     * @param object
     * @return
     */
    public static String getString(Object object) {
        return getString(object, "");
    }

    public static String getString(Object object, String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return object.toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Integer
     * @param object
     * @return
     */
    public static int getInt(Object object) {
        return getInt(object, -1);
    }

    public static int getInt(Object object, Integer defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Boolean
     * @param object
     * @return
     */
    public static boolean getBoolean(Object object) {
        return getBoolean(object, false);
    }

    public static boolean getBoolean(Object object, Boolean defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String encoderPassword(String password) {
        return getMD5Digest32(password);
    }

    public static String getMD5Digest32(String str) {
        if (str == null) {
            return null;
        }

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md.update(str.getBytes());

        return getFormattedText(md.digest());
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }

        return buf.toString();
    }
    private static Pattern pattern = Pattern.compile("\\d+$");
	/**
	 * 获取字符串结尾的数字
	 *
	 * @param str
	 * @return
	 */
	public static String getEndDigit(String str) {
		String result = "";
		if (StringUtils.isEmpty(str)) {
			return result;
		}

		Matcher matcher = pattern.matcher(str);

		if (matcher.find()) {
			result = matcher.group();
		}

		return result;
	}

    public static String trimMark(String str){
        if(str != null && !"".equals(str.trim())){
            return str.replace(" ","").replace("-","").replace("'","");
        }
        return null;
    }

    /**
     * 返回指定格式的日期字符串
     * @param date
     * @param formatter
     * @return
     */
    public static String getFormatterDate(Date date, String formatter){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 判断请求是否是ajax
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(header))return true;
        return false;
    }

    /**
     * 从流读取字符串
     * @param inputStream
     * @return
     */
    public static String getStringFromInputStream(InputStream inputStream){
        String string = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
            String buf = null;
            try {
                while((buf = bufferedReader.readLine()) != null){
                    string += buf;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return string;
    }

    /**
     * 获取指定格式时间再指定分钟后的时间字符串
     * @param date
     * @param formatter
     * @param minites
     * @return
     */
    public static String getFormatterDate(String date,String formatter,int minites){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        String ret = null;
        try {
            Date parse = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.add(Calendar.MINUTE, minites);
            ret = sdf.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 获取指定分钟前的时间
     * @param date
     * @param afterMinites
     * @return
     */
    public static Date getBeforeDate(Date date,int beforeMinites){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -beforeMinites);
        return calendar.getTime();
    }

    /**
     * 获取制定天数前的日期
     * @param date
     * @param beforeDays
     * @return
     */
    public static Date getBeforeDaysDate(Date date,int beforeDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -beforeDays);
        return calendar.getTime();
    }

    /**
     * 生成唯一字符串
     * @return
     */
    public static String generateSn(){
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    public static String getMac(){
        String mac = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            byte[] hardwareAddress = NetworkInterface.getByInetAddress(localHost).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for(int i=0; i<hardwareAddress.length; i++) {
                //字节转换为整数
                int temp = hardwareAddress[i]&0xff;
                String str = Integer.toHexString(temp);
                //System.out.println("每8位:"+str);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
            mac = sb.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mac.toUpperCase();
    }

    public static String readFileToString(File file){
        String string = "";
        if(file != null){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    string += line;
                }
                br.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return string;
    }

    public static void handleFile(File file){
        if(file.isDirectory()){
            File[] listFiles = file.listFiles();
            for(File f : listFiles){
                handleFile(f);
            }
        }else{
            System.out.println(file.getName());
            file.delete();
        }
    }
}
