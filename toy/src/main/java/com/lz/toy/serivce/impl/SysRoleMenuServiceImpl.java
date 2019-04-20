package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lz.toy.entity.SysRoleMenu;
import com.lz.toy.mapper.SysRoleMenuMapper;
import com.lz.toy.serivce.ISysRoleMenuService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * SysRoleMenu 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

	@Override
	public List<SysRoleMenu> selectRoleMenuByUserId(String uid) {
		return baseMapper.selectRoleMenuByUserId(uid);
	}

	@Override
	public void addAuth(String roleId, String[] menuIds) {
		 //删除原有权限
		this.delete(new EntityWrapper<SysRoleMenu>().eq("role_id",roleId));
		 //重新授权
		if(ArrayUtils.isNotEmpty(menuIds)){
			for(String menuId : menuIds){
				SysRoleMenu sysRoleMenu2 = new SysRoleMenu();
				sysRoleMenu2.setRoleId(roleId);
				sysRoleMenu2.setMenuId(menuId);
				this.insert(sysRoleMenu2);
			}
		}
	}
	@Override
	public List<SysRoleMenu> selectByRole(String roleId) {
		EntityWrapper<SysRoleMenu> ew = new EntityWrapper<>();
		ew.addFilter("role_id = {0}", roleId);
		return this.selectList(ew);
		
	}


}