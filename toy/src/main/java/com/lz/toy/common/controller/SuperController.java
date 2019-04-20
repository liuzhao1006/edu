package com.lz.toy.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;

import com.lz.toy.common.bean.Token;
import com.lz.toy.common.util.HttpUtil;
import com.lz.toy.common.util.TokenUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * 基础控制器
 * @author liuzhao
 */
@Log4j
@Getter
@Setter
public class SuperController {

	@Autowired
	protected HttpServletRequest request;


	/**
	 * 返回登录 Token
	 */
	protected Token getSSOToken() {
		Token tk = TokenUtil.getToken(request);
		if (tk == null) {
			throw new RuntimeException("-1,The user does not exist, please relogin.");
		}
		return tk;
	}


	/**
	 * 是否为 post 请求
	 */
	protected boolean isPost() {
		return HttpUtil.isPost(request);
	}


	/**
	 * 是否为 get 请求
	 */
	protected boolean isGet() {
		return HttpUtil.isGet(request);
	}


	/**
	 * <p>
	 * 获取分页对象
	 * </p>
	 * <p>
	 * 每页显示数量
	 *
	 * @return 分页对象
	 */
	protected <T> Page<T> getPage(int pageNumber, int pageSize) {
		return new Page<>(pageNumber, pageSize);
	}


	/**
	 * 重定向至地址 url
	 *
	 * @param url 请求地址
	 * @return 返回地址
	 */
	protected String redirectTo(String url) {
		return "redirect:" + url;
	}

	/**
	 * 返回 JSON 格式对象
	 *
	 * @param object 转换对象
	 * @return json字符串
	 */
	private String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	/**
	 * 返回 JSON 格式对象
	 *
	 * @param object 转换对象
	 *               <p>
	 *               序列化特点
	 */
	protected String toJson(Object object, String format) {

		return format == null ? toJson(object)
				: JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}


}
