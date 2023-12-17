package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.OrderMapper;
import com.company.project.model.Order;
import com.company.project.model.OrderDetails;
import com.company.project.model.User;
import com.company.project.service.MomentCommentService;
import com.company.project.service.OrderDetailsService;
import com.company.project.service.OrderService;
import com.company.project.service.UserService;
import com.company.project.utils.Alipay;
import com.company.project.utils.AlipayConfig;
import com.company.project.utils.DigitUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailsService orderDetailsService;

    @Resource
    private UserService userService;

    @Resource
    private MomentCommentService momentCommentService;

    @Override
    public BigDecimal getTotalMoney(Long goodsId) {

        return  new BigDecimal("0");
    }

    @Override
    public Integer getNumber(Long userId) {
        return orderMapper.getNumber(userId);
    }

    @Override
    public Result pay(String id) {
        Order order = new Order();
        order.setId(Long.valueOf(id));
        //修改支付宝状态
        order.setStatus(2);
        //支付宝回调成功
        update(order);
        return ResultGenerator.genSuccessResult("恭喜您，订单支付成功了。");
    }

    @Override
    public Result add(Order order) {

        order.setId(DigitUtil.generatorLongId());

        //批量添加订单详情
        if (order.getOrderDetailsList().size() > 0){
            for (OrderDetails d: order.getOrderDetailsList()) {
                d.setOrderId(order.getId());
                d.setCreatedAt(new Date());
                d.setIsDelete(false);
            }
            orderDetailsService.save(order.getOrderDetailsList());
        }

        order.setCreatedAt(new Date());
        order.setIsDelete(false);
        order.setStatus(2);
        save(order);

        //调用支付宝直接生成购买效果图，具体图片在upload下 2.jpg
        String html = Alipay.generatePayHtml(String.valueOf(order.getId()), order.getTotalMoney() , "商品购买", "用户购买商品");

        Result result= ResultGenerator.genSuccessResult();
        result.setData(html);

        return result;
    }

    @Override
    public void aliPay(HttpServletResponse httpResponse) {
        //调用支付宝直接生成购买效果图，具体图片在upload下 2.jpg
        String html = Alipay.generatePayHtml(String.valueOf(DigitUtil.generatorLongId()), new BigDecimal("100") , "商品购买", "用户购买商品");
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        try {
            httpResponse.getWriter().write(html);
            // 直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void wapPay(HttpServletResponse httpResponse) {
        //调用支付宝直接生成购买效果图，具体图片在upload下 2.jpg
        String html = Alipay.generateWapPayHtml(String.valueOf(DigitUtil.generatorLongId()), new BigDecimal("100") , "商品购买", "用户购买商品");
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        try {
            httpResponse.getWriter().write(html);
            // 直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result list(Order order) {

        if (null != order.getCreatedBy()){
            User user = userService.findByIdAndValidDelete(Long.valueOf(order.getCreatedBy()));
            if (user != null){
                if (0 == user.getRoleId() || 2 == user.getRoleId()){
                    order.setCreatedBy(null);
                }
            }
        }

        PageHelper.startPage(order.getPage() == null ? 0 : order.getPage(), order.getLimit() == null ? 10 : order.getLimit());
        order.setIsDelete(false);
        List<Order> list = orderMapper.list(order);
        for (Order d: list) {
            d.setOrderDetailsList(orderDetailsService.selectByOrderId(d.getId()));
            if (d.getCreatedBy() != null){
                d.setUser(userService.findByIdAndValidDelete(Long.valueOf(d.getCreatedBy())));
            }
            d.setMomentCommentList(momentCommentService.selectByMomentId(d.getId()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result detail(Long id) {
        Order order = orderMapper.detail(id);
        if (null != order){
            order.setOrderDetailsList(orderDetailsService.selectByOrderId(order.getId()));
            order.setUser(userService.findByIdAndValidDelete(Long.valueOf(order.getCreatedBy())));
        }
        return ResultGenerator.genSuccessResult(order);
    }

    @Override
    public BigDecimal getMarketCount() {
        return orderMapper.getMarketCount();
    }

    @Override
    public BigDecimal getTodayCount(Date date) {
        return orderMapper.getTodayCount(date);
    }

    @Override
    public BigDecimal getMarketOrderCount() {
        return orderMapper.getMarketOrderCount();
    }

    @Override
    public BigDecimal getTodayOrderCount(Date date) {
        return orderMapper.getTodayOrderCount(date);
    }

    @Override
    public Result updateGoods(Order order) {

        /*Goods goods = goodsService.findById(orderDetailsService.selectByOrderId(order.getId()).get(0).getGoodsId());
        if (null == goods){
            return ResultGenerator.genFailResult(ResultCode.GOODS_NULL_ERROR,"商品不存在，请重新归还");
        }*/

        order.setUpdatedAt(new Date());
        update(order);

        //如果归还成功，库存+1
        /*if (7 == order.getStatus()){
            //归还成功，库存+1
            goods.setRepertory(goods.getRepertory().add(BigDecimal.valueOf(1)));
            goodsService.update(goods);
        }*/

        Result result= ResultGenerator.genSuccessResult();
        result.setData(order);
        return result;
    }

}
