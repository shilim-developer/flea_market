package com.market.service;

import com.market.exception.ParamsException;
import com.market.model.Good;
import com.market.model.ResultMessage;

import java.util.List;

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
	public abstract ResultMessage<Good> getGoodById(Good good);
	public abstract ResultMessage<String> addGood(Good good) throws ParamsException;
	public abstract ResultMessage<String> updateGood(Good good) throws ParamsException;
	public abstract ResultMessage<String> deleteGood(List<Good> goods) throws ParamsException;
}
