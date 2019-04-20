package com.lz.toy.serivce.impl;


import com.lz.toy.common.bean.Token;
import com.lz.toy.serivce.IPermissionService;
import com.lz.toy.serivce.ISysMenuService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 权限service实现
 * @author liuzhao
 */
@Log4j
@Service
public class PermissionServiceImpl implements IPermissionService {

	private final ISysMenuService sysMenuService;

	@Autowired
	public PermissionServiceImpl(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}


	public boolean hasPermission(Token token, String permission) {
		/*if(token == null){
			throw new RuntimeException("非法的用户");
		}
		List<String> permissionList =  sysMenuService.selectMenuIdsByUserId(token.getUid());
		if(permissionList.contains(permission)){
			return true;
		}
		log.warn(String.format("无[%s]访问权限,uid:%s",permission,token.getUid()));
		return false;*/
		return true;
	}

}