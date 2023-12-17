package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.OrderDetails;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsService extends Service<OrderDetails> {

    List<OrderDetails> selectByOrderId(Long id);

    int getCountByGoodsId(Long goodsId);

    BigDecimal getSaleCount(Long id);
}
