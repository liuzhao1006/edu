package com.lz.toy.common.util;


import com.lz.toy.common.bean.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Token操作工具类
 * @author liuzhao
 */
public class TokenUtil {

	/**
	 * 存储Token信息到Session
	 * @param token token令牌
	 * @param request 请求
	 */
	public static void addToken(Token token, HttpServletRequest request){
		request.getSession().setAttribute("token", token);
	}
	
	/**
	 * 清理Token
	 * @param request 请求
	 */
	public static void clearLogin(HttpServletRequest request){
		request.getSession().removeAttribute("token");
	}
	/**
	 * 清理Token并重定向到登录页面
	 * @param request 请求
	 * @param response 响应
	 */
	public static void clearRedirectLogin(HttpServletRequest request,HttpServletResponse response){
		try {
			clearLogin(request);
			//response.sendRedirect("/login?return_url="+URLEncoder.encode(request.getRequestURL().toString(),"UTF-8"));
			response.sendRedirect("/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Token
	 * @param request HttpServletRequest
	 * @return Token
	 */
	public static Token getToken(HttpServletRequest request){
		Object object = request.getSession().getAttribute("token");
		if(object == null){
			return null;
		}
		return (Token) object;
	}
	
}
