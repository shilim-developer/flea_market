package com.market.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Message;
import com.market.model.Order;
import com.market.model.ResultMessage;
import com.market.model.User;
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
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	
	@RequestMapping("/getPageOrderByBuyUserId")
	public ResultMessage<Page<Order>> getPageOrderByBuyUserId(String page,HttpSession session) throws Exception {
		return orderService.getPageOrderByBuyUserId(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				new Order().setBuyUserId(((User)session.getAttribute("user")).getuId())
				);
	}
	
	@RequestMapping("/addOrder")
	public ResultMessage<String> addOrder(String order,HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");
		return orderService.addOrder(JsonUtil.jsonToObject(order, Order.class)
				.setBuyUser(user.getUsername()).setBuyUserId(user.getuId()));
	}
	
	@RequestMapping("/changeOrderStatus")
	public ResultMessage<String> changeOrderStatus(String order) throws Exception {
		return orderService.addOrder(JsonUtil.jsonToObject(order, Order.class));
	}

}

