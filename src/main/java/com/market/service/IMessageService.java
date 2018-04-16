package com.market.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.market.exception.ParamsException;
import com.market.model.Message;
import com.market.model.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
public interface IMessageService extends IService<Message> {
	public abstract ResultMessage<Page<Message>> getPageMessageListByToUser(Page<Message> page, Message message) throws ParamsException;
	public abstract ResultMessage<Page<Message>> getPageMessageListByStatus(Page<Message> page, Message message) throws ParamsException;
	public abstract ResultMessage<String> sendMessage(Message message) throws ParamsException;
	public abstract ResultMessage<String> deleteMessage(List<Message> messages) throws ParamsException;
}
