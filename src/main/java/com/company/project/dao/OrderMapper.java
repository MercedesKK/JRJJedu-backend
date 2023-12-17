package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    List<Order> list(Order order);

    Order detail(Long id);

    BigDecimal getMarketCount();

    BigDecimal getTodayCount(Date date);

    BigDecimal getTodayOrderCount(Date date);

    BigDecimal getMarketOrderCount();

    Integer getNumber(Long userId);
}
