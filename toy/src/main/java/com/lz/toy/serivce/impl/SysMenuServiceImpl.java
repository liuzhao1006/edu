package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lz.toy.common.anno.Time;
import com.lz.toy.common.util.CommonUtil;
import com.lz.toy.entity.SysMenu;
import com.lz.toy.entity.SysRoleMenu;
import com.lz.toy.entity.vo.SysMenus;
import com.lz.toy.entity.vo.TreeMenu;
import com.lz.toy.entity.vo.TreeMenuAllowAccess;
import com.lz.toy.mapper.SysMenuMapper;
import com.lz.toy.serivce.ISysMenuService;
import com.lz.toy.serivce.ISysRoleMenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * SysMenu 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
	/**
	 * 角色菜单关系服务
	 */
	private ISysRoleMenuService sysRoleMenuService;

	public SysMenuServiceImpl(ISysRoleMenuService sysRoleMenuService) {
		this.sysRoleMenuService = sysRoleMenuService;
	}
	@Time
	@Cacheable(value = "menuCache", key = "#uid")
	@Override
	public List<SysMenus> selectSysMenuByUserId(String uid) {
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectRoleMenuByUserId(uid);
		List<String> roleMenus = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
		String s = Joiner.on(",").join(roleMenus);
		EntityWrapper<SysMenu> ew = new EntityWrapper<>();
		ew.lt("deep",3).in("id",s).orderBy("code,sort");
		List<SysMenu> sysMenus = baseMapper.selectList(ew);
		List<SysMenu> collect = sysMenus.stream().filter(sysMenu -> sysMenu.getDeep() == 1).collect(Collectors.toList());
		List<SysMenus> menuss = new ArrayList<>();
		for (SysMenu menu:collect) {
			SysMenus menus = new SysMenus();
			menus.setSysMenu(menu);
			menus.setChildren(sysMenus.
					stream().
					filter(sysMenu ->
							sysMenu.getPid().equalsIgnoreCase(menu.getId()))
					.collect(Collectors.toList()));
			menuss.add(menus);
		}
		return menuss;
	}

	@Cacheable(value = "permissionCache", key = "#uid")
	@Override
	public List<String> selectMenuIdsByUserId(String uid) {
		// TODO Auto-generated method stub
		return baseMapper.selectMenuIdsByUserId(uid);
	}

	@Cacheable(value = "menuCache", key = "#uid")
	@Override
	public List<TreeMenu> selectTreeMenuByUserId(String uid) {
		// TODO Auto-generated method stub
		/**
		 * 当前用户二级菜单权限
		 */
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectRoleMenuByUserId(uid);
		/**
		 * 当前用户菜单主键
		 */
		List<String> menuIds = new ArrayList<>();
		for(SysRoleMenu sysRoleMenu : sysRoleMenus){
			menuIds.add(sysRoleMenu.getMenuId());
		}
		return selectTreeMenuByMenuIdsAndPid(menuIds, "0");
	}
	
	@Override
	public List<TreeMenu> selectTreeMenuByMenuIdsAndPid(final List<String> menuIds,
			String pid) {
		// TODO Auto-generated method stub
		EntityWrapper<SysMenu> ew = new EntityWrapper<>();
		ew.orderBy("sort", true);
		ew.eq("pid", pid);
		ew.in("id", menuIds.size() > 0 ? menuIds : Lists.newArrayList(CommonUtil.randomNumeric(30)));
		List<SysMenu> sysMenus = this.selectList(ew);
		List<TreeMenu> treeMenus = new ArrayList<>();
		for(SysMenu sysMenu : sysMenus){
			TreeMenu treeMenu = new TreeMenu();
			treeMenu.setSysMenu(sysMenu);
			if(sysMenu.getDeep() < 2){
				treeMenu.setChildren(selectTreeMenuByMenuIdsAndPid(menuIds,sysMenu.getId()));
			}
			treeMenus.add(treeMenu);
		}
		return treeMenus;
	}
	
	@Time
	@Override
	public List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(
			final List<String> menuIds, String pid) {
		// TODO Auto-generated method stub
		EntityWrapper<SysMenu> ew = new EntityWrapper<>();
		ew.orderBy("sort", true);
		ew.eq("pid", pid);
		List<SysMenu> sysMenus = this.selectList(ew);

		List<TreeMenuAllowAccess> treeMenuAllowAccesss = new ArrayList<>();
		for(SysMenu sysMenu : sysMenus){
			TreeMenuAllowAccess treeMenuAllowAccess = new TreeMenuAllowAccess();
			treeMenuAllowAccess.setSysMenu(sysMenu);
			if(menuIds.contains(sysMenu.getId())){
				treeMenuAllowAccess.setAllowAccess(true);
			}
			if(sysMenu.getDeep() < 3){
				treeMenuAllowAccess.setChildren(selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds,sysMenu.getId()));
			}
			treeMenuAllowAccesss.add(treeMenuAllowAccess);
		}
		return treeMenuAllowAccesss;
	}

	@Time
	@Override
	public List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPidByRole(
			final List<String> menuIds, String pid, String roleId) {
		EntityWrapper<SysMenu> ew = new EntityWrapper<>();
		ew.orderBy("sort", true);
		ew.eq("pid", pid);
		List<SysMenu> sysMenuList = this.selectList(ew);
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectList(new EntityWrapper<SysRoleMenu>().eq("role_id", roleId));
		List<String> roleMenuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
		List<SysMenu> sysMenus = sysMenuList.stream().filter(m -> roleMenuIds.contains(m.getId())).collect(Collectors.toList());

		List<TreeMenuAllowAccess> treeMenuAllowAccesss = new ArrayList<>();
		for(SysMenu sysMenu : sysMenus){
			TreeMenuAllowAccess treeMenuAllowAccess = new TreeMenuAllowAccess();
			treeMenuAllowAccess.setSysMenu(sysMenu);
			if(menuIds.contains(sysMenu.getId())){
				treeMenuAllowAccess.setAllowAccess(true);
			}
			if(sysMenu.getDeep() < 3){
				treeMenuAllowAccess.setChildren(selectTreeMenuAllowAccessByMenuIdsAndPidByRole(menuIds,sysMenu.getId(),roleId));
			}
			treeMenuAllowAccesss.add(treeMenuAllowAccess);
		}
		return treeMenuAllowAccesss;
	}

}