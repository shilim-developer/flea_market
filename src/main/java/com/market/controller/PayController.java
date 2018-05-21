package com.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.market.exception.ParamsException;
import com.market.model.Order;
import com.market.service.IOrderService;

@Controller
public class PayController {
	@Autowired
	IOrderService orderService;
	
	// 支付成功回调
	@RequestMapping("user/payCallback")
	public String payCallback(String r1_Code,String r2_TrxId,String r6_Order) throws ParamsException {
		if(r1_Code.equals("1")) {
			Order order = new Order();
			order.setOrderNumber(r6_Order);
			order.setPaySerialsNumber(r2_TrxId);
			order.setStatus(1);
			orderService.changeOrderStatus(order);
			return "redirect:http://tomcat.shilim.cn/market-front/#/orderList";
		}
		return null;
	}
}
