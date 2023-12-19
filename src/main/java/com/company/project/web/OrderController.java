package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Order;
import com.company.project.service.OrderService;
import com.company.project.utils.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/order")
@Api(tags = {"/order"}, description = "订单管理模块")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @ApiOperation(value = "下单", notes = "下单")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Order order) {
        System.out.println(order.getStatus());
        order.setCreatedBy(super.getUserId());
        return orderService.add(order);
    }

    @ApiOperation(value = "支付宝电脑网站支付", notes = "支付宝电脑网站支付")
    @RequestMapping(value = "/aliPay", method = {RequestMethod.GET})
    public void aliPay(HttpServletResponse httpResponse) {
        orderService.aliPay(httpResponse);
    }

    @ApiOperation(value = "支付宝移动网站支付", notes = "支付宝移动网站支付")
    @RequestMapping(value = "/wapPay", method = {RequestMethod.GET})
    public void wapPay(HttpServletResponse httpResponse) {
        orderService.wapPay(httpResponse);
    }

    @ApiOperation(value = "删除订单", notes = "删除订单")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Order order=new Order();
        order.setId(id);
        order.setIsDelete(true);
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "归还", notes = "归还")
    @RequestMapping(value = "/updateGoods", method = {RequestMethod.POST})
    public Result updateGoods(@RequestBody Order order) {
        return orderService.updateGoods(order);
    }

    @ApiOperation(value = "获取订单单个详情", notes = "获取订单单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return orderService.detail(id);
    }

    @ApiOperation(value = "支付宝回调", notes = "支付宝回调")
    @RequestMapping(value = "/ali/pay/notify/url", method = {RequestMethod.GET})
    public Result pay(@RequestParam String out_trade_no) {
        Logger.info(this,"支付宝回调接口入参--》" + out_trade_no);
        return orderService.pay(out_trade_no);
    }

    @ApiOperation(value = "分页查询订单", notes = "分页查询订单")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Order order) {
        if (null == order){
            order = new Order();
        }
        order.setCreatedBy(super.getUserId());
        return orderService.list(order);
    }
}
