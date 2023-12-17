package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.OrderDetailsMapper;
import com.company.project.model.OrderDetails;
import com.company.project.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderDetailsServiceImpl extends AbstractService<OrderDetails> implements OrderDetailsService {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public List<OrderDetails> selectByOrderId(Long orderId) {
        return orderDetailsMapper.selectByOrderId(orderId);
    }

    @Override
    public int getCountByGoodsId(Long goodsId) {
        return orderDetailsMapper.getCountByGoodsId(goodsId);
    }

    @Override
    public BigDecimal getSaleCount(Long goodsId) {

        BigDecimal sale = new BigDecimal("0");

        return sale;
    }

}
