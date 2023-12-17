package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Api(tags = {"/file"},description="文件上传模块")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传单个文件返回全路径", notes = "上传单个文件返回全路径")
    @RequestMapping(value = "/uploadSingle", method = {RequestMethod.POST})
    public Result uploadSingle(HttpServletRequest request,MultipartFile file){
        return fileService.uploadSinglePhoto(request,file);
    }

    @ApiOperation(value = "获取视频时长", notes = "获取视频时长")
    @RequestMapping(value = "/getVideoDuration", method = {RequestMethod.POST})
    public Result getVideoDuration(@RequestParam(value = "fileUrl", required = false) String fileUrl){
        return fileService.getVideoDuration(fileUrl);
    }

    /*@ApiOperation(value = "上传单个文件只返回名称", notes = "上传单个文件只返回名称")
    @RequestMapping(value = "/uploadSingleName", method = {RequestMethod.POST})
    public Result uploadSingleName(HttpServletRequest request,MultipartFile file){
        return fileService.uploadSingleName(request,file);
    }*/

    /*@ApiOperation(value = "上传单个文件返回全路径", notes = "上传单个文件返回全路径")
    @RequestMapping(value = "/uploadSingle", method = {RequestMethod.POST})
    public Result uploadSingle(HttpServletRequest request,MultipartFile file){
        return fileService.uploadSingle(request,file);
    }*/

}
