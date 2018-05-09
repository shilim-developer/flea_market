package com.market.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.market.constant.ResultCode;
import com.market.exception.ParamsException;
import com.market.model.ResultMessage;
import com.market.utils.JsonUtil;

public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		PrintWriter wirter = null;
		ResultMessage<String> resultMessage;
		if(exception instanceof ParamsException) {
			resultMessage = new ResultMessage<String>(true, ResultCode.FAIL, exception.getMessage(), null);
		} else {
			resultMessage = new ResultMessage<String>(true, ResultCode.FAIL, "系统错误", null);
		}
		if(exception != null) {
			try {
				response.setContentType("text/html;charset=UTF-8");
				wirter = response.getWriter();
				wirter.write(JsonUtil.objectToJson(resultMessage));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(wirter != null) {
					wirter.flush();
					wirter.close();
				}
			}
		}
		return null;
	}

}
