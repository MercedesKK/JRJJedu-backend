package com.company.project.utils;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;

public class VideoTimeUtil {

    /**
     * 视频时长
     * @param fileUrl
     * @return String[] 0=秒时长，1=展示时长（格式如 01:00:00）
     */
    public static String[] parseDuration(String fileUrl) {
        String[] length = new String[2];
        try {
            //
//            URL source = new URL(fileUrl);
            // 构造方法 接受URL对象
//            MultimediaObject instance = new MultimediaObject(source);
            // 构造方法 接受File对象
            MultimediaObject instance = new MultimediaObject(new File(fileUrl));
            MultimediaInfo result = instance.getInfo();
            Long ls = result.getDuration() / 1000;
            length[0] = String.valueOf(ls);
            Integer hour = (int) (ls / 3600);
            Integer minute = (int) (ls % 3600) / 60;
            Integer second = (int) (ls - hour * 3600 - minute * 60);
            String hr = hour.toString();
            String mi = minute.toString();
            String se = second.toString();
            if (hr.length() < 2) {
                hr = "0" + hr;
            }
            if (mi.length() < 2) {
                mi = "0" + mi;
            }
            if (se.length() < 2) {
                se = "0" + se;
            }

            String noHour = "00";
            if (noHour.equals(hr)) {
                length[1] = mi + ":" + se;
            } else {
                length[1] = hr + ":" + mi + ":" + se;
            }

        } catch (Exception e) {

        }
        return length;
    }

    public static void main(String[] args){
        String[] a = parseDuration(System.getProperty("user.dir") +  Constants.PROJECT_FILE_URL + "/20230902/1693650039885.mp4");
        //String[] a = parseDuration("E:\\1693221792447video02.mp4");
        System.out.println(a[1]);
    }

}
