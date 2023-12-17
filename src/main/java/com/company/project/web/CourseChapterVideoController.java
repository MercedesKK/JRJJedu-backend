package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CourseChapterVideo;
import com.company.project.service.CourseChapterVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/course/chapter/video")
@Api(tags = {"/course/chapter/video"}, description = "章节视频管理模块")
public class CourseChapterVideoController extends BaseController {
    @Resource
    private CourseChapterVideoService courseChapterVideoService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody CourseChapterVideo courseChapterVideo) {
        courseChapterVideo.setCreatedAt(new Date());
        courseChapterVideo.setIsDelete(false);
        courseChapterVideo.setCreatedBy(super.getUserId());
        return courseChapterVideoService.add(courseChapterVideo);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        CourseChapterVideo courseChapterVideo=new CourseChapterVideo();
        courseChapterVideo.setId(id);
        courseChapterVideo.setIsDelete(true);
        courseChapterVideoService.update(courseChapterVideo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody CourseChapterVideo courseChapterVideo) {
        courseChapterVideo.setUpdatedAt(new Date());
        courseChapterVideoService.update(courseChapterVideo);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(courseChapterVideo);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        return courseChapterVideoService.detail(id);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) CourseChapterVideo courseChapterVideo) {
        return courseChapterVideoService.list(courseChapterVideo);
    }
}
