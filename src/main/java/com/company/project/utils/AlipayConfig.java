package com.company.project.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    //参数暂时顶死，稍后改为获取配置文件信息

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String app_id = "9021000132688644";

    //商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFfYxhs9KVibLpToPe9BojJ3Q1zb05O2HyZBu8bOxG3rYSJC3xtGL2C7XIhpUXMyDEJKq2KCo4x8NSV3lCxlJ0Px3Kc3f9G8+NRLeYQe6ifizK73gocYJSz/q2WQnpdu7vL/dEn/IBGYRff2co+xpDwd6P0pxE4slW9VRJElBYH39OAt45kG/SpEZrWoftz5gcR1Pz9qfY+MdluOayoCPfyX8BFXXg7Jf+exSpPvhNVGZX2IphR+U4wuhTa0l33RnevEsqcGyb95X/FT3HqCzY4hHUiFSpA+QnP/zRszXzUxY19Q2jsygn6jpXg2pl99g19mJ7S61WQ+hdgrHeNi/5AgMBAAECggEAN9KOt/TI5TvWE3Z/8uZFX9oJ4M4OiTZp+UEf0CYEkIZlwSRcCi2TDK1M8XRGOJNq6lsjAFW/gksXRQHLdb8VkAMs5BJxZOkSXFMWK5e2wUDeXmbR6qe9BgBvKX4GhRj1RhTa5Fm2i/stvfm7HAoVq6wQ9DfwnWXxKGojOwNrkc3PVOkI33S0jgcfxMkOT/OLYa2NRhetAY+bNaY6VTQgvrRqOnLj2G6lwICdhhoY1V1N+aRMi9Md6k0b/T2XoUmKJsffZKZDWeqjHsgCdy1ijEVbReKL4x9NB5ZokYkaeGrhgVsssNYSFeoSA6HLdrQ8PamJW9urbqbfopIUPZ6MWQKBgQDCIcXzBQ3jQS5QADx0dHVPvlytfm8w1GtdwDORYvDcm+KFKAihx2mEAoxkj/ASXkyxDPDSCKfhrJVQaPHrydcPzP4CbD27dprb3xXXlW9VF2oA+nyiODl70BnGIVaxn8HUcY3t7SlYscaoaS22pk85xoG1J7do/YVEaEmrpnWq1wKBgQCwCFip41vu51OSIlMV3+ZUWWyX9xSKmot2adF7PboYg9cM1uQnq7aLnPfXguJKbe+YiDeQvTZZxUePCea0K16BCJbAVyt2RvPfrmtB9er1vl5h3Tkr1u7WDT+VFvwi3eBp1oSfVHgcIfmSz9QrWK+iby14YyG3IEjih3yzpV7xrwKBgF104NTTdBIZSPBejLwLbIZNQFSNb330ZROm+axeE1JBAVXVJgKr+1H/4BJxcMOG/iil8DFM1XVBHkitnPlEG/KRXx9PeJCM0pRS2zMDz6sJMJD0BZgatSGz1xSY7CFqpWdem1MVPzhhrR9aMxWhDrbjp4HMcvFqSwPZqM2nvzHVAoGADHwLDcF6O69cVfjpO5vtkf3lncGAS3Q6f+NxX/Q4xpRTJC35cHbVM4GmcjpuIik53O+MQU+qEyPSt2pUWmLZCP2681k7U+AkW7DbGop+Z39g4j/2Vg2gUr4NzF0c9LwXtmnobgRnoFcohCkJOynHCxYne2pMcdhwUuCZD4Si/zECgYEAnweFiN1A7YOuj1c2zO2I4Mr9cJkx74N96JY9ZBTARJKdKG/Ps7ex1H5rh47barkcq/NlHjDGY+Gf1qkuDwYxHyOXUzb0D0E9D2fOlQIAvKFVnKprQeaxHnGLjC3HRT0Y8XZTmTZKkST10I7MSE7wSQTcrclI4FsKoPJ4ehq/JF4=";

    //商户公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。和应用公钥完全没关系
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvNTNX8FEHnx4xs4PD9x+xMiVl/1zeLGnGWn5gPw8N5/+NdIsL81CsaZp0giKX8q9u8iAGKO2PwzQxsArJXUz+pqMUsgKVWjTZQGkEksGLUkBkMPuSdAHH9ETdLrt8nakcJUljQlRnMB+oY8kTC9qylTQMrLXTsiWiCHR8Jmv4av7OqUrZtIWxk9E4fSZbzRDryWDJiWO3wnTKMhdLl7jvA3wrj2b4/7aIW+VFa6UI/lsHyyy6bU/CRgt/UOjE1OIWOmBl/R6QGDeLJSUc51LU33lOiCX8owuKd7ov9trXQh5pjetWdzW+daprysR9mjk5IsJg43TJsBvsvk+EWOdIQIDAQAB";

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
