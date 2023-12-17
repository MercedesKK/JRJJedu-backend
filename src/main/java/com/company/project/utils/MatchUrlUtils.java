package com.company.project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@CacheConfig(cacheNames = "docheckUrls")
@Configuration
@Component
public class MatchUrlUtils {

    private Pattern pattern = null;
    private Matcher match = null;
    private boolean tag=false;

    @Value("${taskmagprops.checkLoginUrl}")
    private String checkLoginUrl;

    //@Cacheable("docheckUrls")
    public boolean checkUrls(String url) {
        Logger.info(this,"------检查入参URL------："+url);
        return isMatchUrl(checkLoginUrl, url);
    }

    private boolean isMatchUrl(String urls, String url) {
        tag = false;

        if (null == urls) {
            return tag;
        }
        List<String> urlsList = Arrays.asList(urls.split(","));
        urlsList.forEach(pathUrl -> {
            if (pathUrl.contentEquals(url)) {
                tag= true;// 匹配成功
                return;
            }
            pattern = Pattern.compile(pathUrl);
            match = pattern.matcher(url);
            if (match.matches()) {
                tag= true; // 正在匹配成功
            }
        });

        return tag;
    }
}

