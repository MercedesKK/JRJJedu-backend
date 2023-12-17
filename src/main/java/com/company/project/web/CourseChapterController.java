package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CourseChapter;
import com.company.project.service.CourseChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/course/chapter")
@Api(tags = {"/course/chapter"}, description = "章节管理模块")
public class CourseChapterController extends BaseController {
    @Resource
    private CourseChapterService courseChapterService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody CourseChapter courseChapter) {
        courseChapter.setCreatedAt(new Date());
        courseChapter.setIsDelete(false);
        courseChapter.setCreatedBy(super.getUserId());
        return courseChapterService.add(courseChapter);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        CourseChapter courseChapter=new CourseChapter();
        courseChapter.setId(id);
        courseChapter.setIsDelete(true);
        courseChapterService.update(courseChapter);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody CourseChapter courseChapter) {
        courseChapter.setUpdatedAt(new Date());
        courseChapterService.update(courseChapter);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(courseChapter);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        return courseChapterService.detail(id);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) CourseChapter courseChapter) {
        return courseChapterService.list(courseChapter);
    }
}
