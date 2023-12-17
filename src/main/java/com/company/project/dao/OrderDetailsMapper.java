package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.OrderDetails;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsMapper extends Mapper<OrderDetails> {

    List<OrderDetails> selectByOrderId(Long orderId);

    int getCountByGoodsId(Long goodsId);

    BigDecimal getTotalNumberByGoodsId(Long goodsId);
}
