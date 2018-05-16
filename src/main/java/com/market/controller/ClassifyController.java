package com.market.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.reflect.TypeToken;
import com.market.model.Classify;
import com.market.model.ResultMessage;
import com.market.service.IClassifyService;
import com.market.utils.JsonUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liwei
 * @since 2018-04-10
 */
@RestController
@RequestMapping("/classify")
public class ClassifyController {
	@Autowired
	IClassifyService classifyService;
	
	@RequestMapping("/getAllClassify")
	public ResultMessage<List<Classify>> getAllClassify() {
		return classifyService.getAllClassify();
	}
	
	@RequestMapping("/admin/getClassifyByPage")
	public ResultMessage<Page<Classify>> getClassifyByPage(String page) throws Exception {
		return classifyService.getClassifyByPage(JsonUtil.jsonToObject(page, new TypeToken<Page<Classify>>(){}.getType()));
	}
	
	@RequestMapping("/getClassifyById")
	public ResultMessage<Classify> getClassifyById(String classify) throws Exception {
		return classifyService.getClassifyById(JsonUtil.jsonToObject(classify, Classify.class));
	} 
	
	@RequestMapping("/admin/addClassify")
	public ResultMessage<String> addClassify(String classify) throws Exception {
		return classifyService.addClassify(JsonUtil.jsonToObject(classify, Classify.class));
	}
	
	@RequestMapping("/admin/updateClassify")
	public ResultMessage<String> updateClassify(String classify) throws Exception {
		return classifyService.updateClassify(JsonUtil.jsonToObject(classify, Classify.class));
	}
	
	@RequestMapping("/deleteClassify")
	public ResultMessage<String> deleteClassify(String classifies) throws Exception {
		return classifyService.updateClassify(JsonUtil.jsonToObject(classifies, new TypeToken<List<Classify>>(){}.getType()));
	}
}

