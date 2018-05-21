package com.market.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.market.exception.ParamsException;

/**
 * json与对象转换工具类
 *
 */
public class JsonUtil {
	private static Gson gson;

	static{
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gson =builder.create();
	}

	/**
	 * json字符串转换成对象
	 * @param json
	 * @param clazz
	 * @return T
	 */
	public static <T> T jsonToObject(String json,Class<T> clazz) throws Exception {
		T result = null;
		try {
			result = gson.fromJson(json, clazz);
		} catch (Exception e) {
			throw new ParamsException("请检查表单填写是否符合规范");
		}
		return result;
	}

	/**
	 * json字符串转换成对象
	 * @param json
	 * @param type (new TypeToken<List<T>>(){}.getType() ,new TypeToken<Map<T,T>>(){}.getType())
	 * @return T
	 */
	public static <T> T jsonToObject(String json,Type type) throws Exception {
		T result = null;
		try {
			result = gson.fromJson(json, type);
		} catch (Exception e) {
			throw new ParamsException("请检查表单填写是否符合规范");
		}
		return result;
	}

	/**
	 * 对象转换成json字符串
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) throws Exception {
		return gson.toJson(object);
	}

}
