package com.market.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/classify")
public class ClassifyController {
	@Autowired
	IClassifyService classifyService;
	
	@RequestMapping("/getAllClassify")
	public ResultMessage<List<Classify>> getAllClassify() {
		return classifyService.getAllClassify();
	}
	
	@RequestMapping("/getClassifyById")
	public ResultMessage<Classify> getClassifyById(String classify) throws Exception {
		return classifyService.getClassifyById(JsonUtil.jsonToObject(classify, Classify.class));
	} 
	
	@RequestMapping("/addClassify")
	public ResultMessage<String> addClassify(String classify) throws Exception {
		return classifyService.addClassify(JsonUtil.jsonToObject(classify, Classify.class));
	}
	
	@RequestMapping("/updateClassify")
	public ResultMessage<String> updateClassify(String classify) throws Exception {
		return classifyService.updateClassify(JsonUtil.jsonToObject(classify, Classify.class));
	}
	
	@RequestMapping("/deleteClassify")
	public ResultMessage<String> deleteClassify(String classifies) throws Exception {
		return classifyService.updateClassify(JsonUtil.jsonToObject(classifies, new TypeToken<List<Classify>>(){}.getType()));
	}
}

