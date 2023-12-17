package com.company.project.utils;

public final class Constants {

    //用户登录token
    public static final String REDIS_KEY_LOGIN = "xc_user_login";

    //找回密码token
    public static final String REDIS_KEY_PASSWORD = "xc_user_password";

    public static final String REDIS_KEY_VERFIY = "xc_user_verfiycode";

    public static final String TOKEN_NAME = "accessToken";

    //过期时间毫秒 180000  = 3分钟
    public static final long verifyCodeForTempValidTime = 180000;

    //访问本地图片路径 http://localhost:8066/file/a112d9f0-b3a4-4833-b4b6-d1e1171fc2ea.png
    //public static final String WINDOWS_FILE_USER = "http://352n16b397.wicp.vip:";

    //访问本地图片路径 http://localhost:8066/file/a112d9f0-b3a4-4833-b4b6-d1e1171fc2ea.png
    //public static final String WINDOWS_FILE_URL = "http://localhost:";

    //访问线上图片路径 http://121.4.242.133:8066/file/a112d9f0-b3a4-4833-b4b6-d1e1171fc2ea.png
    public static final String LINUX_FILE_USER = "http://121.4.242.133:";

    public static final String OPTIONS_METHOD = "OPTIONS";

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN_YMD = "yyyy-MM-dd";

    //资源位于项目中的路径
    public static final String PROJECT_FILE_URL = "\\src\\main\\resources\\upload\\";

    //管控台的endpoint地址
    public static final String END_POINT = "oss-cn-hangzhou.aliyuncs.com";

    //accessKeyId
    public static final String ACCESS_KEY_ID = "LTAI5tDFs33ATcGdgFAG9iVn";

    //secretKey
    public static final String ACCESS_KEY_SECRET = "icMtVH27DFWbl21ohmqnilpLWxL7FO";

    //你创建的bucket名称
    public static final String BUCKET_NAME = "mingzhe-file";

}
