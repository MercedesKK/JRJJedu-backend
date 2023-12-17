package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.MomentLike;
import com.company.project.service.MomentLikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/moment/like")
@Api(tags = {"/moment/like"}, description = "点赞管理模块")
public class MomentLikeController extends BaseController {
    @Autowired
    private MomentLikeService momentLikeService;

    @ApiOperation(value = "点赞或者取消点赞", notes = "点赞或者取消点赞")
    @RequestMapping(value = "/addOrCancel", method = {RequestMethod.POST})
    public Result addOrCancel(@RequestBody MomentLike momentLike) {
        momentLike.setCreatedBy(super.getUserId());
        return momentLikeService.addOrCancel(momentLike);
    }

    @ApiOperation(value = "删除点赞", notes = "删除点赞")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        MomentLike momentLike=new MomentLike();
        momentLike.setId(id);
        momentLike.setIsDelete(true);
        momentLikeService.update(momentLike);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改点赞", notes = "修改点赞")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody MomentLike momentLike) {
        momentLike.setUpdatedAt(new Date());
        momentLikeService.update(momentLike);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(momentLike);
        return result;
    }

    @ApiOperation(value = "获取点赞单个详情", notes = "获取点赞单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询单个详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new MomentLike());
        }else {
            MomentLike momentLike = momentLikeService.findById(id);
            if (null == momentLike){
                return ResultGenerator.genSuccessResult(new MomentLike());
            }else {
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(momentLike);
            }
        }
    }

    @ApiOperation(value = "分页查询点赞", notes = "分页查询点赞")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) MomentLike momentLike) {
        return momentLikeService.list(momentLike);
    }
}
