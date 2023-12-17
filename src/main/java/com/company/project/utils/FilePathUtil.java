package com.company.project.utils;

public final class FilePathUtil {

    /**
     * Prefix for OSX, MS and Linux
     */
    public static final String OS_PREFIX;

    static {
        if (OSinfo.isWindows()) {
            OS_PREFIX = "D:\\home\\java\\file\\";
        } else if (OSinfo.isLinux()) {
            OS_PREFIX = "/home/java/file/";
        } else if (OSinfo.isMacOS()) {
            OS_PREFIX = "/Users/java/file";
        } else {
            OS_PREFIX = "D:\\home\\java\\file\\";
        }
    }

    /**
     * 获取Windows下项目路径 + 项目中资源路径 = 文件总路径
     * @return
     */
    public static String filePath(){
        return System.getProperty("user.dir") +  Constants.PROJECT_FILE_URL;
    }

}
