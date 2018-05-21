package com.market.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.market.constant.ResultCode;
import com.market.dao.UserDao;
import com.market.exception.ParamsException;
import com.market.model.ResultMessage;
import com.market.model.User;
import com.market.service.IUserService;
import com.market.utils.ValidatorUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
	@Autowired
	ValidatorUtil<User> validatorUtil;

	@Override
	public ResultMessage<String> register(User user) throws ParamsException {
		validatorUtil.validate(user,User.Register.class);
		User rUser = user.selectOne(new EntityWrapper<User>().eq("username", user.getUsername()));
		ResultMessage<String> resultMessage;
		if(rUser == null) {
			user.insert();
			resultMessage = new ResultMessage<String>(true,ResultCode.SUCCESS,"注册成功",null);
		} else {
			resultMessage = new ResultMessage<String>(true,ResultCode.FAIL,"账号已存在",null);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<User> login(User user, HttpSession session) throws ParamsException {
		validatorUtil.validate(user,User.Login.class);
		User rUser = user.selectOne(
				new EntityWrapper<User>()
				.eq("username", user.getUsername())
				.eq("password", user.getPassword()));
		ResultMessage<User> resultMessage;
		if(rUser != null) {
			session.setAttribute("user", rUser);
			rUser.setPassword(null);
			resultMessage = new ResultMessage<User>(true,ResultCode.SUCCESS,"登录成功",rUser);
		} else {
			resultMessage = new ResultMessage<User>(true,ResultCode.FAIL,"账号或者密码错误",null);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<String> logout(HttpSession session) {
		session.removeAttribute("user");
		return new ResultMessage<String>(false,ResultCode.SUCCESS,"注销成功",null);
	}

	@Override
	public ResultMessage<User> getUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ResultMessage<User> resultMessage;
		if(user == null) {
			resultMessage = new ResultMessage<User>(true,ResultCode.NO_LOGIN,"尚未登录",null);
		} else {
			resultMessage = new ResultMessage<User>(true,ResultCode.SUCCESS,"获取成功",user.selectById().setPassword(null));
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<String> updatePassword(User user,HttpSession session) throws ParamsException {
		validatorUtil.validate(user,User.UpdatePassword.class);
		User rUser = (User) session.getAttribute("user");
		rUser = rUser.selectById();
		ResultMessage<String> resultMessage = null;
		if(rUser.getPassword().equals(user.getOldPassword())) {
			rUser.setPassword(user.getPassword());
			updateById(rUser);
			resultMessage = new ResultMessage<String>(false,ResultCode.SUCCESS,"修改成功",null);
		} else {
			resultMessage = new ResultMessage<String>(false,ResultCode.FAIL,"旧密码不正确",null);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage<String> updatePhone(User user,HttpSession session) throws ParamsException {
		validatorUtil.validate(user);
		User rUser = (User) session.getAttribute("user");
		rUser = rUser.selectById();
		rUser.setPhone(user.getPhone());
		updateById(rUser);
		return new ResultMessage<String>(false,ResultCode.SUCCESS,"修改成功",null);
	}

	@Override
	public ResultMessage<String> updateInformation(User user) throws ParamsException {
		validatorUtil.validate(user,User.UpdateUser.class);
		User rUser = user.selectById();
		rUser.setNickname(user.getNickname()).setSex(user.getSex())
		.setPhone(user.getPhone()).setEmail(user.getEmail());;
		updateById(rUser);
		return new ResultMessage<String>(false,ResultCode.SUCCESS,"修改成功",null);
	}

}
