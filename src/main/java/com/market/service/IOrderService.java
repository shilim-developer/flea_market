package com.market.service;

import com.market.exception.ParamsException;
import com.market.model.Order;
import com.market.model.ResultMessage;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
public interface IOrderService extends IService<Order> {
	ResultMessage<Page<Order>> getPageOrderByBuyUserId(Page<Order> page, Order order) throws ParamsException;

	ResultMessage<Page<Order>> getPageOrderBySoldUserId(Page<Order> page, Order order) throws ParamsException;

	ResultMessage<String> addOrder(Order order) throws ParamsException;

	ResultMessage<String> changeOrderStatus(Order order) throws ParamsException;

	ResultMessage<String> getPayUrl(Order order) throws ParamsException;

}
