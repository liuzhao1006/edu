package com.lz.toy.serivce;


import com.lz.toy.common.bean.Token;

/**
 * 权限服务接口
 *  @author liuzhao
 */
public interface IPermissionService {
	
	/**
	 * 判断某个用户是否拥有指定权限
	 */
	boolean hasPermission(Token token, String ermission);
	
	
}