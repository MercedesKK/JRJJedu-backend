package com.company.project.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.service.FileService;
import com.company.project.utils.Constants;
import com.company.project.utils.FilePathUtil;
import com.company.project.utils.StringUtil;
import com.company.project.utils.VideoTimeUtil;
import com.company.project.vo.VideoUrlVo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    String endpoint = Constants.END_POINT;
    String accessKeyId = Constants.ACCESS_KEY_ID;
    String accessKeySecret = Constants.ACCESS_KEY_SECRET;
    String bucketName = Constants.BUCKET_NAME;

    //项目路径
    private static String uploadDir = FilePathUtil.filePath();

    //判断系统
    private static String sysDir = FilePathUtil.OS_PREFIX;

    @Override
    public Result uploadFile(HttpServletRequest request, MultipartFile file) {

        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        if (file.getSize() > 5000000){
            return ResultGenerator.genFailResult("该文件过大，请重新上传");
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            // 获取文件名
            String fileName = file.getOriginalFilename();

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + fileName;

            //调用oss方法上传到阿里云
            //第一个参数：Bucket名称
            //第二个参数：上传到oss文件路径和文件名称
            //第三个参数：上传文件输入流
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
            //上传
            ossClient.putObject(putObjectRequest);
            //PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            VideoUrlVo videoUrlVo = new VideoUrlVo();

            //把上传后把文件url返回
            //https://xppll.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

            //同步上传本地数据
            Result result = uploadSingleName(request,file);

            videoUrlVo.setRemoteVideoUrl(url);
            videoUrlVo.setLocalVideoUrl(result.getData().toString());

            return ResultGenerator.genSuccessResult(videoUrlVo);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ossClient != null) {
                //关闭OSSClient
                ossClient.shutdown();
            }
        }

        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    /**
     * 本地文件服务器(区分Linux和Windows和Mac操作系统)
     * @param request
     * @param file
     * @return
     */
    @Override
    public Result uploadSinglePhoto(HttpServletRequest request, MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            file.transferTo(new File(uploadDir + "/" + fileName));
            String path;
            if (sysDir.contains("D")){
                // request.getScheme() 获取请求的协议名称
                // request.getServerName() 获取请求的域名
                // request.getServerPort() 获取请求的端口号
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(path);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }


    @Override
    public Result uploadSingleName(HttpServletRequest request, MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadDir + "/" + fileName));
            String path;
            if (sysDir.contains("D")){
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(fileName);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    @Override
    public Result uploadSingle(HttpServletRequest request,MultipartFile file) {
        if (null == file){
            return ResultGenerator.genFailResult(ResultCode.FILE_BULL_ERROR,"文件不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!sysDir.contains("D")){
            //Linux环境
            uploadDir = "/home/java/file/";
        }

        //准备保存文件
        File filePath = new File(uploadDir);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        filePath = new File(uploadDir + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //判断当天日期的文件夹是否存在，若不存在，则创建
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }

        fileName = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadDir + "/" + fileName));
            String path;
            if (sysDir.contains("D")){
                path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + fileName;
            }else {
                //Linux环境
                path = Constants.LINUX_FILE_USER + request.getServerPort() + "/file/" + fileName;
            }
            return ResultGenerator.genSuccessResult(path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResultGenerator.genFailResult(ResultCode.FILEUPLOAD_ERROR,"文件上传失败");
    }

    @Override
    public Result getVideoDuration(String fileUrl) {
        String[] file = VideoTimeUtil.parseDuration(fileUrl);
        return ResultGenerator.genSuccessResult(file[1]);
    }

}
