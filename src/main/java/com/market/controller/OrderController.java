package com.market.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.exception.ParamsException;
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
	
	@RequestMapping("/getPageOrderBySoldUserId")
	public ResultMessage<Page<Order>> getPageOrderBySoldUserId(String page,HttpSession session) throws Exception {
		return orderService.getPageOrderBySoldUserId(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				new Order().setSoldUserId(((User)session.getAttribute("user")).getuId())
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
	
	@RequestMapping("user/getPayUrl")
	public ResultMessage<String> getPayUrl(String order) throws Exception {
		return orderService.getPayUrl(JsonUtil.jsonToObject(order, Order.class));
	}
	
	@RequestMapping("user/payCallback")
	public String payCallback(String r1_Code,String r2_TrxId,String r6_Order) throws ParamsException {
		if(r1_Code.equals("1")) {
			Order order = new Order();
			order.setOrderNumber(r6_Order);
			order.setPaySerialsNumber(r2_TrxId);
			order.setStatus(1);
			orderService.changeOrderStatus(order);
			return "redirect:http://127.0.0.1:8081/#/orderList";
		}
		return null;
	}
	
	@RequestMapping("user/deliver")
	public ResultMessage<String> deliver(String order) throws Exception {
		Order rOrder = JsonUtil.jsonToObject(order, Order.class);
		return orderService.changeOrderStatus(rOrder.setStatus(2));
	}
	
	@RequestMapping("user/receipt")
	public ResultMessage<String> receipt(String order) throws Exception {
		Order rOrder = JsonUtil.jsonToObject(order, Order.class);
		return orderService.changeOrderStatus(rOrder.setStatus(3));
	}

}

