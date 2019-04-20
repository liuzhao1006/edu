package com.lz.toy.serivce;

import com.baomidou.mybatisplus.service.IService;
import com.lz.toy.entity.SysRoleMenu;

import java.util.List;


/**
 *
 * SysRoleMenu 表数据服务层接口
 * @author liuzhao
 *
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
	
	/**
	 * 角色授权
	 */
	void addAuth(String roleId, String[] menuIds);
	
	/**
	 * 获取指定角色的权限
	 */
	List<SysRoleMenu> selectByRole(String roleId);

	/**
	 * 根据用户Id获取用户所在角色的权限
	 */
	List<SysRoleMenu> selectRoleMenuByUserId(String uid);



}