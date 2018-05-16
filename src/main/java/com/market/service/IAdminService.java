package com.market.service;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.service.IService;
import com.market.exception.ParamsException;
import com.market.model.Admin;
import com.market.model.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liwei
 * @since 2018-05-14
 */
public interface IAdminService extends IService<Admin> {
	public abstract ResultMessage<Admin> login(Admin admin,HttpSession session) throws ParamsException;
	public abstract ResultMessage<String> logout(HttpSession session);
	public abstract ResultMessage<Admin> getAdmin(HttpSession session);
}
