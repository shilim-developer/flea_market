package com.market.service.impl;

import com.market.model.Good;
import com.market.model.ResultMessage;
import com.market.constant.ResultCode;
import com.market.dao.GoodDao;
import com.market.exception.ParamsException;
import com.market.service.IGoodService;
import com.market.utils.ValidatorUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-04-15
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodDao, Good> implements IGoodService {
	@Autowired
	ValidatorUtil<Good> validatorUtil;

	@Override
	public ResultMessage<List<Good>> getAllGood() {
		return new ResultMessage<List<Good>>(true, ResultCode.SUCCESS, "获取成功", selectList(null));
	}

	@Override
	public ResultMessage<Page<Good>> getGoodByPage(Page<Good> page) {
		return new ResultMessage<Page<Good>>(true, ResultCode.SUCCESS, "获取成功", selectPage(page));
	}

	@Override
	public ResultMessage<Good> getGoodById(Good good) {
		return new ResultMessage<Good>(true, ResultCode.SUCCESS, "获取成功", selectById(good.getgId()));
	}

	@Override
	public ResultMessage<String> addGood(Good good) throws ParamsException {
		validatorUtil.validate(good);
		insert(good);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "添加成功", null);
	}

	@Override
	public ResultMessage<String> updateGood(Good good) throws ParamsException {
		validatorUtil.validate(good);
		updateById(good);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "修改成功", null);
	}

	@Override
	public ResultMessage<String> deleteGood(List<Good> goods) throws ParamsException {
		List<Integer> ids = new ArrayList<>();
		for(Good good : goods) {
			validatorUtil.validate(good);
			ids.add(good.getcId());
		}
		deleteBatchIds(ids);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "删除成功", null);
	}

}
