package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.CollectionList;
import com.company.project.service.CollectionListService;
import com.company.project.common.BaseController;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/collection/list")
@Api(tags = {"/collection/list"}, description = "合集管理模块")
public class CollectionListController extends BaseController{
    @Resource
    private CollectionListService collectionListService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody CollectionList collectionList) {
        collectionList.setCreatedAt(new Date());
        collectionList.setIsDelete(false);
        collectionList.setCreatedBy(super.getUserId());
        collectionListService.save(collectionList);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(collectionList);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        CollectionList collectionList=new CollectionList();
        collectionList.setId(id);
        collectionList.setIsDelete(true);
        collectionListService.update(collectionList);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody CollectionList collectionList) {
        collectionList.setUpdatedAt(new Date());
        collectionListService.update(collectionList);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(collectionList);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new CollectionList());
        }else {
            CollectionList collectionList = collectionListService.findById(id);
            if (null == collectionList){
                return ResultGenerator.genSuccessResult(new CollectionList());
            }else {
                return ResultGenerator.genSuccessResult(collectionList);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) CollectionList collectionList) {

        if (null == collectionList){
            collectionList = new CollectionList();
        }

        PageHelper.startPage(collectionList.getPage() == null ? 0 : collectionList.getPage(), collectionList.getLimit() == null ? 10 : collectionList.getLimit());
        collectionList.setIsDelete(false);
        List<CollectionList> list = collectionListService.findByModel(collectionList);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
