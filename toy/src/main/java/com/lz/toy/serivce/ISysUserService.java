package com.lz.toy.serivce;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lz.toy.entity.SysUser;
import com.lz.toy.entity.vo.UserCompany;

import java.util.HashMap;
import java.util.List;


/**
 *
 * SysUser 表数据服务层接口
 * @author liuzhao
 *
 */
public interface ISysUserService extends IService<SysUser> {

	/**
	 * 分页查询用户
	 */
	Page<UserCompany> selectUserList(Page<UserCompany> page, HashMap<String, Object> param);

	/**
	 * 保存用户
	 */
	void insertUser(SysUser user, String[] roleId);

	/**
	 * 保存岗位,角色
	 *
	 */

	void insertUserInfo(SysUser sysUser, String[] roleId, Integer[] postId);
	/**
	 * 更新用户
	 */
	void updateUser(SysUser sysUser, String[] roleId);

	/**
	 * 更新用户
	 */
	void updateUserInfo(SysUser sysUser, String[] roleId, Integer[] postId);
	/**
	 * 登录
	 */
	SysUser login(String userName, String password);

	/**
	 * 删除用户
	 */
	void delete(String id);

	List<SysUser> sysUserAllList(Integer unitId);
	UserCompany selectUserDetailById(Integer id);
	UserCompany selectLeadDetailById(Integer id);
}