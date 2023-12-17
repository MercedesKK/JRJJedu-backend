package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.model.Order;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;

public interface OrderService extends Service<Order> {

    Result add(Order order);

    Result list(Order order);

    Result detail(Long id);

    Result updateGoods(Order order);

    BigDecimal getMarketCount();

    BigDecimal getTodayCount(Date date);

    BigDecimal getMarketOrderCount();

    BigDecimal getTodayOrderCount(Date date);

    BigDecimal getTotalMoney(Long id);

    Integer getNumber(Long userId);

    Result pay(String out_trade_no);

    void aliPay(HttpServletResponse httpResponse);

    void wapPay(HttpServletResponse httpResponse);
}
