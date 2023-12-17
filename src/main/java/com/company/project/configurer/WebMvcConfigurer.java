package com.company.project.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;
import com.company.project.utils.FilePathUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    //项目路径
    private static String uploadDir = FilePathUtil.filePath();

    //判断系统
    private static String sysDir = FilePathUtil.OS_PREFIX;

    /*@Value("${file.url}")
    private String uploadDir;*/

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        //fast json格式化返回信息 WriteMapNullValue 保留空的字段 DisableCircularReferenceDetect 循环引用检测
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);

        //保留空的字段
        //config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        //禁止循环引用检测
        // (在传输的数据中出现相同的对象时，fastjson默认开启引用检测将相同的对象写成引用的形式
        // "$ref": "$.data.list[2].caseShowList[0]")
        //但是如果禁用之后要小心出现内存内漏错误
        //config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 按需配置，更多参考FastJson文档哈

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("*").allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        if (!"dev".equals("dev")) { //开发环境忽略签名认证
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    //验证签名
                    boolean pass = validateSign(request);
                    if (pass) {
                        return true;
                    } else {
                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                        Result result = new Result();
                        result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                        responseResult(response, result);
                        return false;
                    }
                }
            });
        }
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//排除sign参数
        Collections.sort(keys);//排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5

        return StringUtils.equals(sign, requestSign);//比较
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }
        return ip;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //springboot 直接访问静态资源(界面)
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //场景：部署的Springboot项目，打成jar包，需要通过url直接访问jar外部路径的资源(相当于直接访问Linux文件夹的数据)
        //http://121.4.242.133:7004/image/home.jpg(意思是说我只要访问image，就会去获取/home/java/file的文件)
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        //registry.addResourceHandler("/image/**").addResourceLocations("file:///home/java/");

        //判断系统是否包含D，如果包含D的话，服务为Windows环境，Windows环境下图片存放在本地项目中
        if (sysDir.contains("D")){
            // 如果不知道项目具体路径 可以查看 https://blog.csdn.net/Hu531053/article/details/102895336 该篇文章

            // uploadDir该路径为项目所在具体路径 如果项目是在D盘或者是E盘的话 格式如下
            // D:\\project\\spring-boot-scaffold\\src\\main\\resources\\upload\\
            // E:\\project\\spring-boot-scaffold\\src\\main\\resources\\upload\\
            registry.addResourceHandler("/file/**").addResourceLocations("file:///" + uploadDir);
        }else {
            //Linux环境
            registry.addResourceHandler("/file/**").addResourceLocations("file:///home/java/file/");
        }
        super.addResourceHandlers(registry);
    }

    /**
     * 配置静态资源(图片和视频)Linux和Windows版本
     * @param
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
     registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
     registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
     //场景：部署的Springboot项目，打成jar包，需要通过url直接访问jar外部路径的资源(相当于直接访问Linux文件夹的数据)
     //http://121.4.242.133:7004/image/home.jpg(意思是说我只要访问image，就会去获取/home/java/file的文件)
     //addResourceHandler是指你想在url请求的路径
     //addResourceLocations是图片存放的真实路径
     //registry.addResourceHandler("/image/**").addResourceLocations("file:///home/java/");
     //判断系统是否包含D，如果包含D的话，服务为Windows环境
     if (uploadDir.contains("D")){
     registry.addResourceHandler("/file/**").addResourceLocations("file:///D:/home/java/file/");
     }else {
     //Linux环境
     registry.addResourceHandler("/file/**").addResourceLocations("file:///home/java/file/");
     }
     super.addResourceHandlers(registry);
     }*/
}
