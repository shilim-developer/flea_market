package com.market.service.impl;

import com.market.model.Order;
import com.market.model.ResultMessage;
import com.market.constant.ResultCode;
import com.market.dao.OrderDao;
import com.market.exception.ParamsException;
import com.market.service.IOrderService;
import com.market.utils.ValidatorUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		insert(order);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "下单成功", null);
	}

	@Override
	public ResultMessage<String> changeOrderStatus(Order order) throws ParamsException {
		validatorUtil.validate(order);
		Order tOrder = selectById(order.getoId());
		updateById(tOrder.setStatus(order.getStatus()));
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "修改成功", null);
	}

}
