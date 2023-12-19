package com.company.project.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    //参数暂时顶死，稍后改为获取配置文件信息

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String app_id = "9021000133614080";

    //商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXn+CQ8yLNve8DtFbe3s4bCuFJmVzmBkYwSt/H50IkPNlEbR59Y8go72ILyLU7DObDet84dHdcKyZfhSFiQLvczskuEy4yAioGCF8+RUIdrErDIM2oJqSxRJzU0qO/sFw07xXpOwSaoGa2V1Cg/K8/N4FT/yOZxfPh6KIsHGDJga5pvYYPp4NwJx6OzdHkTHJg/TfBGiBz3g1lNx52bXfBWSOsp48pRKc+NipsTqn2HYBuKBj+3HwvD60hK6ZSXRujy/Gv48KXt+DAZwdsGP+WFW8nGvVxlIr+lUmEgYIrO+fZIV/10HOXGrh4sJ0cuw5PaYlUyKpRx02MJ8F8WgWHAgMBAAECggEBAJcOLYDa6UKFxbgwExeoazdfyIA1bOqxN33nKjiSuT8269GS0HxlS0UU794DkCILQV9ga0BoL33Ac1NYY6DqEv7xeFYRv8UMiOdHsfNstmivfDAX3yt1jEAathAE5iQTxFjsFC0uofCWep2QMYChHMpjNtIC7mnFX6ohxGcjwn0cz5uZl5JEMmzZtiO3CtHlrqeKeqJlo3v6sdi1Vwy6z5m1in/qlQUx57aw4+P4jETwmfiDAhXxYQfkfM4edOjes5A3S/q+har3G/AyfCGoJ0miXGD+5vZQEWe3eOmoIwZQqvCwJLMtqtH4t3ah3KKHEhlhk73kUkC8v3+eVLnZ0+kCgYEAxxqsckx+qefwu5DsN8NMNjAOdx+fmaJunUMPnKGogcRXiWFNviTu7fihilY2PYooxQnP6YbY48L69ozc0nqJaw1yjLzxJctzqccroupdAUJ2GXgDOYCgTWyiBQx2Rzz73HOjEV/kVEbRJ/puOJMzeGgTIMBB1AE7573GVAYBkNMCgYEAwvPcl1w+lqUm8jmIglRI0BlC9wyu2gNwHPQlSfM4Pfxj3yHF8Y+0LcJ3iqu9MuBlTDXTsBEirDC6ZHEX3TZ3pxJTLs8Xy/jtLcYfDg7bxMrFt2dBtCZIPz/FvkV7yvmnQ+09ItO45ksUCtX/CM5Zvmelhy1XBWiOYGvcT7izZ/0CgYEAxA0LyPBxuLPeY5LsRmxT2vevBrVo3ksHSGsEMGMuHblLyMa7WoPbkQ/FYQZLGoW0Z3Cl/Xj+kCcqHyPNPKPUNEllhhd2DJUtKvwnh5/mPJtI1wNJiZGSRyq0zuvae4MdLZHf+/fESQgCY69AYQzxHZq9Wv5002Dzr9k6NPpFCGkCgYBAmHLICD2IxtIfwmZz4J97rFMe7qnk/Oyh+1d7qEZDa6Rf453ly1rFKsFXRk5bVDTcSp2IojTd3wtzinGAz7JXvl+ni+DzqdhUqWfSzaOOEi3u9hy0J1ca9fN8dyNsfsooXKw8JPEIBWpvqLZnYZAk4czb/G7wENPUKQkHtUhktQKBgEO/yx+2zZolijUo/bIj1rJXP/EWE3T36ZCuuetq9a5ihYFw98BpVNf713NzBF5ZN2ALmlHrl2fY/kuOAnziGjBLLmB6d+cT9Aaqnu1u9csxhSy0QEaNb8gcfD+CKrxDK217QQN9Q17Je6JD66tPd6wX2W5zPfP3/8geyV7kR5zH";
    //商户公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。和应用公钥完全没关系
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs2euXd674u2PJSxzo+Gsyvj9CFdgP6Ix4ypJ8cFBUOqnPW+3opTH278omKTh0YM7hbWcUqYc0brOjcYLQstLbNWvjlpvxA3+senKviUhKNhawgAi9d21xu93+MLQFqDuxLxPaklmAkCWweSUnooF0wmQj+RlkKmzXAfgrVW8lkW1r7ZM9OUeEaMAUEXnXQgnnCLdgFIuZEJPaxKOZKtRVhXc5LHAqxvmtrN4xipGqk+6682ZfLMUHQKsRGKx7HMDclcIc0+X4IpQ58YnyKPYxHRnhUCEEqE0gduhvsoRv8OuT6NB3j9WYfch+IEMHvUZwc+Jh2aXRDInRwRgr136nwIDAQAB";
    //页面跳转异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String notify_url = "http://b239r28036.wicp.vip/order/ali/pay/notify/url";

    //页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://b239r28036.wicp.vip/order/ali/pay/notify/url";
    //public static String return_url = "http://352n16b397.wicp.vip/static/start/index.html#/mall-mannger/order/list";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
