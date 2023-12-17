package com.company.project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taskmagprops")
public class MagConfig {

    //300分钟
    @Value("${expireTime:300}")
    private Long expireTime;

    @Value("${checkLoginUrl:123}")
    private String checkLoginUrl;

    @Value("${filedir:123}")
    private String filedir;

    @Value("${fileBasePath:123}")
    private String fileBasePath;

    public String getFileBasePath() {
        return fileBasePath;
    }

    public void setFileBasePath(String fileBasePath) {
        this.fileBasePath = fileBasePath;
    }

    public String getFiledir() {
        return filedir;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }

    public String getCheckLoginUrl() {
        return checkLoginUrl;
    }

    public void setCheckLoginUrl(String checkLoginUrl) {
        this.checkLoginUrl = checkLoginUrl;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

}
