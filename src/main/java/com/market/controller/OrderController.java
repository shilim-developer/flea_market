package com.market.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Message;
import com.market.model.Order;
import com.market.model.ResultMessage;
import com.market.service.IOrderService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	
	@RequestMapping("/getPageOrderByBuyUserId")
	public ResultMessage<Page<Order>> getPageOrderByBuyUserId(String page,String order) throws Exception {
		return orderService.getPageOrderByBuyUserId(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				JsonUtil.jsonToObject(order, Order.class)
				);
	}
	
	@RequestMapping("/addOrder")
	public ResultMessage<String> addOrder(String order) throws Exception {
		return orderService.addOrder(JsonUtil.jsonToObject(order, Order.class));
	}
	
	@RequestMapping("/changeOrderStatus")
	public ResultMessage<String> changeOrderStatus(String order) throws Exception {
		return orderService.addOrder(JsonUtil.jsonToObject(order, Order.class));
	}

}

