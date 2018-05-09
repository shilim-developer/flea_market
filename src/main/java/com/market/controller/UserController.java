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
		System.out.println(user);
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
}

