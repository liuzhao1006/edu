package com.lz.toy.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lz.toy.entity.SysRoleMenu;

import java.util.List;


/**
 *
 * SysRoleMenu 表数据库控制层接口
 * @author liuzhao
 *
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	/**
	 * 根据用户Id获取用户所在角色的权限
	 */
	 List<SysRoleMenu> selectRoleMenuByUserId(String uid);
	
}