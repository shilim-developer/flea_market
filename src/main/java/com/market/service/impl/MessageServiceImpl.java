package com.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.market.constant.ResultCode;
import com.market.dao.MessageDao;
import com.market.exception.ParamsException;
import com.market.model.Message;
import com.market.model.ResultMessage;
import com.market.model.User;
import com.market.service.IMessageService;
import com.market.utils.ValidatorUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liwei
 * @since 2018-04-16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements IMessageService {
	@Autowired
	ValidatorUtil<Message> validatorUtil;

	@Override
	public ResultMessage<Page<Message>> getPageMessageListByToUser(Page<Message> page, Message message) throws ParamsException {
		validatorUtil.validate(message);
		Page<Message> rPage = selectPage(page, new EntityWrapper<Message>().eq("to_user", message.getToUser()));
		List<Message> records = rPage.getRecords();
		for(int i =0;i<records.size();i++) {
			records.get(i).setFUser(new User().setuId(records.get(i).getFromUser()).selectById().setPassword(null));
		}
		rPage.setRecords(records);
		return new ResultMessage<Page<Message>>(true,ResultCode.SUCCESS,"获取成功",rPage);
	}

	@Override
	public ResultMessage<Page<Message>> getPageMessageListByStatus(Page<Message> page, Message message) throws ParamsException {
		validatorUtil.validate(message);
		return new ResultMessage<Page<Message>>(true,ResultCode.SUCCESS,"获取成功",
				selectPage(page, new EntityWrapper<Message>().eq("status", message.getStatus())));
	}

	@Override
	public ResultMessage<Message> getMessageById(Message message) throws ParamsException {
		message = message.selectById();
		message.setFUser(new User().setuId(message.getFromUser()).selectById());
		return new ResultMessage<Message>(true,ResultCode.SUCCESS,"获取成功", message);
	}
	
	@Override
	public ResultMessage<String> sendMessage(Message message) throws ParamsException {
		validatorUtil.validate(message);
		insert(message);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "添加成功", null);
	}
	
	@Override
	public ResultMessage<String> readMessage(Message message) throws ParamsException {
		validatorUtil.validate(message);
		updateById(message.setStatus(1));
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "阅读成功", null);
	}

	@Override
	public ResultMessage<String> deleteMessage(List<Message> messages) throws ParamsException {
		List<Integer> ids = new ArrayList<>();
		for(Message message : messages) {
			validatorUtil.validate(message);
			ids.add(message.getmId());
		}
		deleteBatchIds(ids);
		return new ResultMessage<String>(true, ResultCode.SUCCESS, "删除成功", null);
	}



}
