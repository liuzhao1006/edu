package com.lz.toy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.lz.toy.entity.SysUser;
import com.lz.toy.entity.vo.UserCompany;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 *
 * SysUser 表数据库控制层接口
 * @author liuzhao
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	List<SysUser> sysUserAllList(Integer unitId);
	UserCompany selectUserDetailById(@Param("id") Integer id);
	UserCompany selectLeadDetailById(@Param("id") Integer id);
	List<UserCompany> selectUserList(Page<UserCompany> page, HashMap<String, Object> param);
}