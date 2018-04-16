package com.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.market.constant.ResultCode;
import com.market.dao.ClassifyDao;
import com.market.exception.ParamsException;
import com.market.model.Classify;
import com.market.model.ResultMessage;
import com.market.service.IClassifyService;
import com.market.utils.ValidatorUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-04-10
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyDao, Classify> implements IClassifyService {
	@Autowired
	ValidatorUtil<Classify> validatorUtil;

	@Override
	public ResultMessage<List<Classify>> getAllClassify() {
		return new ResultMessage<List<Classify>>(true, ResultCode.SUCCESS, "获取成功", selectList(null));
	}

	@Override
	public ResultMessage<Page<Classify>> getClassifyByPage(Page<Classify> page) {
		return new ResultMessage<Page<Classify>>(true, ResultCode.SUCCESS, "获取成功", selectPage(page));
	}
	
	@Override
	public ResultMessage<Classify> getClassifyById(Classify classify) {
		return new ResultMessage<Classify>(true, ResultCode.SUCCESS, "获取成功", selectById(classify.getcId()));
	}

	@Override
	public ResultMessage<String> addClassify(Classify classify) throws ParamsException {
		validatorUtil.validate(classify);
		insert(classify);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "添加成功", null);
	}

	@Override
	public ResultMessage<String> updateClassify(Classify classify) throws ParamsException {
		validatorUtil.validate(classify);
		updateById(classify);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "修改成功", null);
	}

	@Override
	public ResultMessage<String> deleteClassify(List<Classify> classifies) throws ParamsException {
		List<Integer> ids = new ArrayList<>();
		for(Classify classify : classifies) {
			validatorUtil.validate(classify);
			ids.add(classify.getcId());
		}
		deleteBatchIds(ids);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "删除成功", null);
	}

}
