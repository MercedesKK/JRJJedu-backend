package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Collect;
import com.company.project.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/collect")
@Api(tags = {"/collect"}, description = "我的收藏管理模块")
public class CollectController extends BaseController {
    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏或者取消收藏", notes = "收藏或者取消收藏")
    @RequestMapping(value = "/addOrCancel", method = {RequestMethod.POST})
    public Result addOrCancel(@RequestBody Collect collect) {
        collect.setCreatedBy(super.getUserId());
        return collectService.addOrCancel(collect);
    }

    @ApiOperation(value = "分页查询我的收藏", notes = "分页查询我的收藏")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Collect collect) {
        collect.setCreatedBy(super.getUserId());
        return collectService.list(collect);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Collect collect=new Collect();
        collect.setId(id);
        collect.setIsDelete(true);
        collectService.update(collect);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Collect collect) {
        collect.setUpdatedAt(new Date());
        collectService.update(collect);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(collect);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        //根据id查询单个详情
        if (null == id){
            return ResultGenerator.genSuccessResult(new Collect());
        }else {
            Collect collect = collectService.findById(id);
            if (null == collect){
                return ResultGenerator.genSuccessResult(new Collect());
            }else {
                //返回查询的单个详情
                return ResultGenerator.genSuccessResult(collect);
            }
        }
    }


}
