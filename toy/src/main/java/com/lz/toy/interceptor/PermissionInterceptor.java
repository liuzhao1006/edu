package com.lz.toy.interceptor;


import com.lz.toy.common.anno.Permission;
import com.lz.toy.common.bean.Token;
import com.lz.toy.common.util.TokenUtil;
import com.lz.toy.serivce.IPermissionService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 资源拦截器
 * @author liuzhao
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 权限服务
	 */

	private IPermissionService permissionService;

	public PermissionInterceptor(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Token token = TokenUtil.getToken(request);
			Permission permissionSecurity =  handlerMethod.getMethodAnnotation(Permission.class);
			if(permissionSecurity != null){
				if(permissionService.hasPermission(token, permissionSecurity.value())){
					return true;
				}
				request.setAttribute("url",request.getRequestURL());
				request.getRequestDispatcher("/error/illegalAccess").forward(request, response);
				return false;
			}
		}*/
		return true;
	}
}
