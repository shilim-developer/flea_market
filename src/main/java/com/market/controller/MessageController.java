package com.market.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Message;
import com.market.model.ResultMessage;
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
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	IMessageService messageService;
	
	@RequestMapping("/getPageMessageListByToUser")
	public ResultMessage<Page<Message>> getPageMessageListByToUser(String page, String message) throws Exception {
		return messageService.getPageMessageListByToUser(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/getPageMessageListByToUser")
	public ResultMessage<Page<Message>> getPageMessageListByStatus(String page, String message) throws Exception {
		return messageService.getPageMessageListByStatus(
				JsonUtil.jsonToObject(page, new TypeToken<Page<Message>>(){}.getType()), 
				JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/sendMessage")
	public ResultMessage<String> sendMessage(String message) throws Exception {
		return messageService.sendMessage(JsonUtil.jsonToObject(message, Message.class));
	}
	
	@RequestMapping("/deleteMessage")
	public ResultMessage<String> deleteMessage(String messages) throws Exception {
		return messageService.deleteMessage(JsonUtil.jsonToObject(messages, new TypeToken<List<Message>>(){}.getType()));
	}
}

