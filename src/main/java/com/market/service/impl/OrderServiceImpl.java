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
				selectPage(page,new EntityWrapper<Order>().eq("buy_user_id", order.getBuyUserId())));
	}
	
	@Override
	public ResultMessage<Page<Order>> getPageOrderBySoldUserId(Page<Order> page, Order order) throws ParamsException {
		validatorUtil.validate(order);
		return new ResultMessage<Page<Order>>(true, ResultCode.SUCCESS, "获取成功", 
				selectPage(page,new EntityWrapper<Order>().eq("sold_user_id", order.getSoldUserId())));
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
		Order rOrder = selectOne(new EntityWrapper<Order>().eq("order_number", order.getOrderNumber()));
		rOrder.setStatus(order.getStatus());
		if(order.getPaySerialsNumber() != null && order.getPaySerialsNumber() != "") {
			rOrder.setPaySerialsNumber(order.getPaySerialsNumber());
		}
		updateById(rOrder);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "修改成功", null);
	}

	@Override
	public ResultMessage<String> getPayUrl(Order order) throws ParamsException {
		validatorUtil.validate(order);
		Order rOrder = order.selectById();
		ResultMessage<String> resultMessage = null;
		if (rOrder == null) {
			resultMessage = new ResultMessage<String>(true, ResultCode.FAIL, "订单不存在", null);
		} else {
			String p0_Cmd = "Buy", // 业务类型
					p1_MerId = "10001126856", // 商户编号
					p2_Order = rOrder.getOrderNumber() + "", // 订单号
					p3_Amt = "0.01", // 支付金额
					p4_Cur = "CNY", // 交易币种
					p5_Pid = rOrder.getOrderNumber() + "", // 商品名称
					p6_Pcat = "", // 商品种类
					p7_Pdesc = "", // 商品描述
					p8_Url = "http://tomcat.shilim.cn/market/user/payCallback.do", // 支付成功回调
					p9_SAF = "", // 收货地址
					pa_MP = "", // 商户扩展信息
					pd_FrpId = "", // 银行编码
					pr_NeedResponse = "1", // 应答机制
					keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 商户密钥
			String hmac = com.market.utils.PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
					p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
			String url = "https://www.yeepay.com/app-merchant-proxy/node?" + "&p0_Cmd=" + p0_Cmd + "&p1_MerId="
					+ p1_MerId + "&p2_Order=" + p2_Order + "&p3_Amt=" + p3_Amt + "&p4_Cur=" + p4_Cur + "&p5_Pid="
					+ p5_Pid + "&p6_Pcat=" + p6_Pcat + "&p7_Pdesc=" + p7_Pdesc + "&p8_Url=" + p8_Url + "&p9_SAF="
					+ p9_SAF + "&pa_MP=" + pa_MP + "&pd_FrpId=" + pd_FrpId + "&pr_NeedResponse=" + pr_NeedResponse
					+ "&hmac=" + hmac;
			resultMessage = new ResultMessage<String>(true, ResultCode.SUCCESS, "获取成功", url);
		}
		return resultMessage;
	}


}
