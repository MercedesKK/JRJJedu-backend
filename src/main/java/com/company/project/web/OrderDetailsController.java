package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.OrderDetails;
import com.company.project.service.OrderDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order/details")
@Api(tags = {"/order/details"}, description = "订单详情管理模块")
public class OrderDetailsController {
    @Resource
    private OrderDetailsService orderDetailsService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody OrderDetails orderDetails) {
        orderDetails.setCreatedAt(new Date());
        orderDetails.setIsDelete(false);
        orderDetailsService.save(orderDetails);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(orderDetails);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setId(id);
        orderDetails.setIsDelete(true);
        orderDetailsService.update(orderDetails);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody OrderDetails orderDetails) {
        orderDetails.setUpdatedAt(new Date());
        orderDetailsService.update(orderDetails);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(orderDetails);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        OrderDetails orderDetails = orderDetailsService.findById(id);
        return ResultGenerator.genSuccessResult(orderDetails);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) OrderDetails orderDetails) {
        PageHelper.startPage(orderDetails.getPage(), orderDetails.getLimit());
        orderDetails.setIsDelete(false);
        List<OrderDetails> list = orderDetailsService.findByModel(orderDetails);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
