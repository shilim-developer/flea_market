package com.market.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Good;
import com.market.model.ResultMessage;
import com.market.service.IGoodService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-04-15
 */
@Controller
@RequestMapping("/good")
public class GoodController {
	@Autowired
	IGoodService goodService;
	
	@RequestMapping("/getAllGood")
	public ResultMessage<List<Good>> getAllGood() {
		return goodService.getAllGood();
	}
	
	@RequestMapping("/getAllGood")
	public ResultMessage<Page<Good>> getGoodByPage(String page) throws Exception {
		return goodService.getGoodByPage(JsonUtil.jsonToObject(page, new TypeToken<Page<Good>>(){}.getType()));
	}
	
	@RequestMapping("/getGoodById")
	public ResultMessage<Good> getGoodById(String good) throws Exception {
		return goodService.getGoodById(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/addGood")
	public ResultMessage<String> addGood(String good) throws Exception {
		return goodService.addGood(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/updateGood")
	public ResultMessage<String> updateGood(String good) throws Exception {
		return goodService.updateGood(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/deleteGood")
	public ResultMessage<String> deleteGood(String goods) throws Exception {
		return goodService.deleteGood(JsonUtil.jsonToObject(goods,new TypeToken<Page<Good>>(){}.getType()));
	}
}

