package com.market.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.market.constant.ResultCode;
import com.market.dao.OrderDao;
import com.market.exception.ParamsException;
import com.market.model.Good;
import com.market.model.Order;
import com.market.model.ResultMessage;
import com.market.service.IOrderService;
import com.market.utils.TimeUtil;
import com.market.utils.ValidatorUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements IOrderService {
	@Autowired
	ValidatorUtil<Order> validatorUtil;

	@Override
	public ResultMessage<Page<Order>> getPageOrderByBuyUserId(Page<Order> page,Order order) throws ParamsException {
		validatorUtil.validate(order);
		return new ResultMessage<Page<Order>>(true, ResultCode.SUCCESS, "获取成功", 
				selectPage(page,new EntityWrapper<Order>().eq("sold_user_id", order.getBuyUserId())));
	}

	@Override
	public ResultMessage<String> addOrder(Order order) throws ParamsException {
		validatorUtil.validate(order);
		System.out.println(order);
		Good good = new Good().setgId(order.getGoodId()).selectById();
		ResultMessage<String> resultMessage;
		if(good.getGoodSurplus()>0) {
			String orderNum = UUID.randomUUID()+"";
			insert(order.setOrderNumber(orderNum));
			order = order.selectOne(new EntityWrapper<Order>().eq("order_number", orderNum));
			order.setOrderNumber(TimeUtil.getCurrentTimeString("yyyyMMddHHmmss") + order.getGoodId() + order.getoId()).updateById();
			resultMessage = new ResultMessage<String>(true, ResultCode.SUCCESS, "下单成功", null);
		} else {
			resultMessage = new ResultMessage<String>(true, ResultCode.FAIL, "商品余量不足", null);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<String> changeOrderStatus(Order order) throws ParamsException {
		validatorUtil.validate(order);
		Order tOrder = selectById(order.getoId());
		updateById(tOrder.setStatus(order.getStatus()));
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "修改成功", null);
	}

}
