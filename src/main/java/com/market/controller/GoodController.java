package com.market.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.exception.ParamsException;
import com.market.model.Good;
import com.market.model.ResultMessage;
import com.market.model.User;
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
@RestController
@RequestMapping("/good")
public class GoodController {
	@Autowired
	IGoodService goodService;
	
	@RequestMapping("/getAllGood")
	public ResultMessage<List<Good>> getAllGood() {
		return goodService.getAllGood();
	}
	
	@RequestMapping("/getGoodByPage")
	public ResultMessage<Page<Good>> getGoodByPage(String page) throws Exception {
		return goodService.getGoodByPage(JsonUtil.jsonToObject(page, new TypeToken<Page<Good>>(){}.getType()));
	}
	
	@RequestMapping("getPageGoodByClassify")
	public ResultMessage<Page<Good>> getPageGoodByClassify(String page,String good) throws Exception {
		return goodService.getPageGoodByClassify(JsonUtil.jsonToObject(page, new TypeToken<Page<Good>>(){}.getType()),
				JsonUtil.jsonToObject(good, Good.class));
	}
	
	@RequestMapping("user/getGoodByPage")
	public ResultMessage<Page<Good>> getGoodByPageByGood(String page,HttpSession session) throws Exception {
		return goodService.getGoodByPageByGood(JsonUtil.jsonToObject(page, new TypeToken<Page<Good>>(){}.getType()),
				new Good().setuId(((User)session.getAttribute("user")).getuId()));
	}
	
	@RequestMapping("admin/getCheckGoodByPage")
	public ResultMessage<Page<Good>> getCheckGoodByPage(String page) throws Exception {
		return goodService.getCheckGoodByPage(JsonUtil.jsonToObject(page, new TypeToken<Page<Good>>(){}.getType()));
	}
	
	@RequestMapping("/getGoodById")
	public ResultMessage<Map<String,Object>> getGoodById(String good) throws Exception {
		return goodService.getGoodById(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/user/addGood")
	public ResultMessage<String> addGood(String good,HttpSession session,HttpServletRequest request) throws Exception {
		Good rGood = JsonUtil.jsonToObject(good,Good.class);
		rGood.setuId(((User)session.getAttribute("user")).getuId());
		return goodService.addGood(rGood,request);
	}
	
	@RequestMapping("/user/updateGood")
	public ResultMessage<String> updateGood(String good) throws Exception {
		return goodService.updateGood(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/user/deleteGood")
	public ResultMessage<String> deleteGood(String goods) throws Exception {
		return goodService.deleteGood(JsonUtil.jsonToObject(goods,new TypeToken<List<Good>>(){}.getType()));
	}
	
	@RequestMapping("/getUserOtherGood")
	public ResultMessage<List<Good>> getUserOtherGood(String good) throws ParamsException, Exception {
		return goodService.getUserOtherGood(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/user/uploadGoodImage")
	public ResultMessage<String> uploadGoodImage(String good,HttpSession session,HttpServletRequest request) throws Exception {
		Good rGood = JsonUtil.jsonToObject(good,Good.class);
		rGood.setuId(((User)session.getAttribute("user")).getuId());
		return goodService.uploadGoodImage(rGood,request);
	}
	
	@RequestMapping("/admin/passCheck")
	public ResultMessage<String> passCheck(String good) throws Exception {
		return goodService.passCheck(JsonUtil.jsonToObject(good,Good.class));
	}
	
	@RequestMapping("/admin/unpassCheck")
	public ResultMessage<String> unpassCheck(String good) throws Exception {
		return goodService.unpassCheck(JsonUtil.jsonToObject(good,Good.class));
	}
}

