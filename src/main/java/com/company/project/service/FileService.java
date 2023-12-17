package com.company.project.service;

import com.company.project.core.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

    //Result uploadSingle(HttpServletRequest request,MultipartFile file);

    //Result uploadSingleName(HttpServletRequest request, MultipartFile file);

    Result uploadFile(HttpServletRequest request, MultipartFile file);

    Result uploadSinglePhoto(HttpServletRequest request, MultipartFile file);

    /**
     * 获取本地名称
     * @param request
     * @param file
     * @return
     */
    Result uploadSingleName(HttpServletRequest request, MultipartFile file);

    /**
     * 获取本地服务器全部路径
     * @param request
     * @param file
     * @return
     */
    Result uploadSingle(HttpServletRequest request, MultipartFile file);

    Result getVideoDuration(String fileUrl);
}
