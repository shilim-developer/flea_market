package com.market.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.market.constant.ResultCode;
import com.market.dao.AdminDao;
import com.market.exception.ParamsException;
import com.market.model.Admin;
import com.market.model.ResultMessage;
import com.market.service.IAdminService;
import com.market.utils.ValidatorUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-05-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {
	@Autowired
	ValidatorUtil<Admin> validatorUtil;

	@Override
	public ResultMessage<Admin> login(Admin admin, HttpSession session) throws ParamsException {
//		validatorUtil.validate(admin,Admin.Login.class);
		Admin rAdmin = admin.selectOne(
				new EntityWrapper<Admin>()
				.eq("username", admin.getUsername())
				.eq("password", admin.getPassword()));
		ResultMessage<Admin> resultMessage;
		if(rAdmin != null) {
			session.setAttribute("admin", rAdmin);
			rAdmin.setPassword(null);
			resultMessage = new ResultMessage<Admin>(true,ResultCode.SUCCESS,"登录成功",rAdmin);
		} else {
			resultMessage = new ResultMessage<Admin>(true,ResultCode.FAIL,"账号或者密码错误",null);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<String> logout(HttpSession session) {
		session.removeAttribute("admin");
		return new ResultMessage<String>(false,ResultCode.SUCCESS,"注销成功",null);
	}

	@Override
	public ResultMessage<Admin> getAdmin(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ResultMessage<Admin> resultMessage;
		if(admin == null) {
			resultMessage = new ResultMessage<Admin>(true,ResultCode.NO_LOGIN,"尚未登录",null);
		} else {
			admin.setPassword(null);
			resultMessage = new ResultMessage<Admin>(true,ResultCode.SUCCESS,"获取成功",admin);
		}
		return resultMessage;
	}

}
