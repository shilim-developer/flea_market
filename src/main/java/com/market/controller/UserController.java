package com.market.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.model.ResultMessage;
import com.market.model.User;
import com.market.service.IUserService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-04-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/register")
	public ResultMessage<String> register(String user) throws Exception {
		return userService.register(JsonUtil.jsonToObject(user, User.class));
	}
	
	@RequestMapping("/login")
	public ResultMessage<User> login(String user,HttpSession session) throws Exception {
		return userService.login(JsonUtil.jsonToObject(user, User.class), session);
	}
	
	@RequestMapping("/logout")
	public ResultMessage<String> logout(HttpSession session) throws Exception {
		return userService.logout(session);
	}
	
	@RequestMapping("/getUserInfo")
	public ResultMessage<User> getUserInfo(HttpSession session) throws Exception {
		return userService.getUser(session);
	}
	
	@RequestMapping("/updatePassword")
	public ResultMessage<String> updatePassword(String user,HttpSession session) throws Exception {
		return userService.updatePassword(JsonUtil.jsonToObject(user, User.class),session);
	}
	
	@RequestMapping("/updatePhone")
	public ResultMessage<String> updatePhone(String user,HttpSession session) throws Exception {
		return userService.updatePhone(JsonUtil.jsonToObject(user, User.class),session);
	}
	
	@RequestMapping("/updateInformation")
	public ResultMessage<String> updateInformation(String user,HttpSession session) throws Exception {
		User mUser = JsonUtil.jsonToObject(user, User.class).setuId(((User)session.getAttribute("user")).getuId());
		return userService.updateInformation(mUser);
	}
}

