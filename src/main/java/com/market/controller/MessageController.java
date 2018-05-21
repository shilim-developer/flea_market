package com.market.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Message;
import com.market.model.ResultMessage;
import com.market.model.User;
import com.market.service.IMessageService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	IMessageService messageService;
	
	@RequestMapping("/user/getPageMessageListByToUser")
	public ResultMessage<Page<Message>> getPageMessageListByToUser(String page, HttpSession session) throws Exception {
		Message message = new Message().setToUser(((User)session.getAttribute("user")).getuId());
		return messageService.getPageMessageListByToUser(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				message);
	}
	
	@RequestMapping("/user/getPageMessageListByStatus")
	public ResultMessage<Page<Message>> getPageMessageListByStatus(String page, String message) throws Exception {
		return messageService.getPageMessageListByStatus(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/user/getMessageById")
	public ResultMessage<Message> getMessageById(String message) throws Exception {
		return messageService.getMessageById(JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/user/sendMessage")
	public ResultMessage<String> sendMessage(String message,HttpSession session) throws Exception {
		Message rMessage = JsonUtil.jsonToObject(message, Message.class);
		rMessage.setFromUser(((User)session.getAttribute("user")).getuId());
		return messageService.sendMessage(rMessage);
	}
	
	@RequestMapping("/user/readMessage")
	public ResultMessage<String> readMessage(String message) throws Exception {
		return messageService.readMessage(JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/user/deleteMessage")
	public ResultMessage<String> deleteMessage(String messages) throws Exception {
		return messageService.deleteMessage(JsonUtil.jsonToObject(messages, new TypeToken<List<Message>>(){}.getType()));
	}
	
	@RequestMapping("/admin/sendSystemMessage")
	public ResultMessage<String> sendSystemMessage(String message) throws Exception {
		return messageService.sendSystemMessage(JsonUtil.jsonToObject(message, Message.class));
	}
}

