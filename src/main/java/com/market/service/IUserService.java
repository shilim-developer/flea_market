package com.market.service;

import com.market.exception.ParamsException;
import com.market.model.ResultMessage;
import com.market.model.User;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liwei
 * @since 2018-04-12
 */
public interface IUserService extends IService<User> {
	public abstract ResultMessage<String> register(User user) throws ParamsException;
	public abstract ResultMessage<User> login(User user,HttpSession session) throws ParamsException;
	public abstract ResultMessage<String> logout(HttpSession session);
	public abstract ResultMessage<User> getUser(HttpSession session);
}
