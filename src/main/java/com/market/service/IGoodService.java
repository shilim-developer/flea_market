package com.market.service;

import com.market.exception.ParamsException;
import com.market.model.Good;
import com.market.model.ResultMessage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liwei
 * @since 2018-04-15
 */
public interface IGoodService extends IService<Good> {
	public abstract ResultMessage<List<Good>> getAllGood();
	public abstract ResultMessage<Page<Good>> getGoodByPage(Page<Good> page);
	public abstract ResultMessage<Page<Good>> getGoodByPageByGood(Page<Good> page,Good good);
	public abstract ResultMessage<Page<Good>> getCheckGoodByPage(Page<Good> page);
	public abstract ResultMessage<Page<Good>> getPageGoodByClassify(Page<Good> page,Good good);
	public abstract ResultMessage<Map<String,Object>> getGoodById(Good good);
	public abstract ResultMessage<String> addGood(Good good,HttpServletRequest request) throws ParamsException;
	public abstract ResultMessage<String> updateGood(Good good) throws ParamsException;
	public abstract ResultMessage<String> deleteGood(List<Good> goods) throws ParamsException;
	public abstract ResultMessage<List<Good>> getUserOtherGood(Good good) throws ParamsException;
	public abstract ResultMessage<String> uploadGoodImage(Good good,HttpServletRequest request);
	
	
	public abstract ResultMessage<String> passCheck(Good good) throws ParamsException;
	public abstract ResultMessage<String> unpassCheck(Good good) throws ParamsException;
	public abstract ResultMessage<String> subCount(Good good) throws ParamsException;
}
