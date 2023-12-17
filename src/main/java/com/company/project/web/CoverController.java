package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Cover;
import com.company.project.service.CoverService;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/cover")
@Api(tags = {"/cover"}, description = "封面管理模块")
public class CoverController {
    @Resource
    private CoverService coverService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Cover cover) {
        cover.setCreatedAt(new Date());
        cover.setIsDelete(false);
        coverService.save(cover);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(cover);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Cover cover=new Cover();
        cover.setId(id);
        cover.setIsDelete(true);
        coverService.update(cover);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Cover cover) {
        cover.setUpdatedAt(new Date());
        coverService.update(cover);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(cover);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new Cover());
        }else {
            Cover cover = coverService.findById(id);
            if (null == cover){
                return ResultGenerator.genSuccessResult(new Cover());
            }else {
                return ResultGenerator.genSuccessResult(cover);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Cover cover) {

        if (null == cover){
            cover = new Cover();
        }

        PageHelper.startPage(cover.getPage() == null ? 0 : cover.getPage(), cover.getLimit() == null ? 10 : cover.getLimit());
        cover.setIsDelete(false);
        List<Cover> list = coverService.findByModel(cover);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
