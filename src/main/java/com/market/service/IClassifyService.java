package com.market.service;

import com.market.exception.ParamsException;
import com.market.model.Classify;
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
 * @since 2018-04-10
 */
public interface IClassifyService extends IService<Classify> {
	public abstract ResultMessage<List<Classify>> getAllClassify();
	public abstract ResultMessage<Page<Classify>> getClassifyByPage(Page<Classify> page);
	public abstract ResultMessage<Classify> getClassifyById(Classify classify);
	public abstract ResultMessage<String> addClassify(Classify classify) throws ParamsException;
	public abstract ResultMessage<String> updateClassify(Classify classify) throws ParamsException;
	public abstract ResultMessage<String> deleteClassify(List<Classify> classifies) throws ParamsException;
}
