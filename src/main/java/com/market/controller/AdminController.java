package com.market.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.model.Admin;
import com.market.model.ResultMessage;
import com.market.service.IAdminService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-05-14
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IAdminService adminService;
	
	@RequestMapping("/login")
	public ResultMessage<Admin> login(String admin,HttpSession session) throws Exception {
		return adminService.login(JsonUtil.jsonToObject(admin, Admin.class), session);
	}
	
	@RequestMapping("/logout")
	public ResultMessage<String> logout(HttpSession session) throws Exception {
		return adminService.logout(session);
	}
	
	@RequestMapping("/getAdminInfo")
	public ResultMessage<Admin> getUserInfo(HttpSession session) throws Exception {
		return adminService.getAdmin(session);
	}
}

