package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.MomentComment;
import com.company.project.service.MomentCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/moment/comment")
@Api(tags = {"/moment/comment"}, description = "评论管理模块")
public class MomentCommentController extends BaseController {
    @Autowired
    private MomentCommentService momentCommentService;

    @ApiOperation(value = "新增评论", notes = "新增评论")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody MomentComment momentComment) {
        momentComment.setCreatedBy(super.getUserId());
        return momentCommentService.add(momentComment);
    }

    @ApiOperation(value = "删除评论", notes = "删除评论")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        MomentComment momentComment=new MomentComment();
        momentComment.setId(id);
        momentComment.setIsDelete(true);
        momentCommentService.update(momentComment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改评论", notes = "修改评论")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody MomentComment momentComment) {
        momentComment.setUpdatedAt(new Date());
        momentCommentService.update(momentComment);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(momentComment);
        return result;
    }

    @ApiOperation(value = "获取全部评论", notes = "获取全部评论")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return momentCommentService.detail(id);
    }

    @ApiOperation(value = "分页查询评论", notes = "分页查询评论")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) MomentComment momentComment) {
        momentComment.setCreatedBy(super.getUserId());
        return momentCommentService.list(momentComment);
    }
}
