package com.market.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.market.constant.ResultCode;
import com.market.model.ResultMessage;
import com.market.model.User;
import com.market.utils.JsonUtil;

/**
 * 登录拦截器
 * 
 * @author shilim
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		response.addHeader("Access-Control-Allow-Origin","*");
		String requestURI = request.getRequestURI();
		System.out.println(requestURI+"###########");
		if (requestURI.indexOf(".do") > 0 && requestURI.indexOf("login") <= 0 && requestURI.indexOf("register") <= 0) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user != null) {
				System.out.println("已登录");
				return true;
			} else {
				response.setContentType("text/html;charset=UTF8");
				PrintWriter out = response.getWriter();
				out.write(JsonUtil.objectToJson(new ResultMessage<String>(false, ResultCode.NO_LOGIN, "没有登录", null)));
				out.flush();
				out.close();
				return false;
			}
		}else {
			return true;
		}
	}

}
