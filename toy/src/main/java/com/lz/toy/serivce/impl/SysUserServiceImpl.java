package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lz.toy.common.util.CommonUtil;
import com.lz.toy.entity.PostUserInfo;
import com.lz.toy.entity.SysUser;
import com.lz.toy.entity.SysUserRole;
import com.lz.toy.entity.vo.UserCompany;
import com.lz.toy.mapper.SysUserMapper;
import com.lz.toy.serivce.ISysUserRoleService;
import com.lz.toy.serivce.ISysUserService;
import com.lz.toy.serivce.ITbPostUserInfoService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * SysUser 表数据服务层接口实现类
 * @author liuzhao
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final ISysUserRoleService sysUserRoleService;
    private final ITbPostUserInfoService tbPostUserInfoService;


    public SysUserServiceImpl(ISysUserRoleService sysUserRoleService, ITbPostUserInfoService tbPostUserInfoService) {
        this.sysUserRoleService = sysUserRoleService;
        this.tbPostUserInfoService = tbPostUserInfoService;

    }
    @Override
    public void insertUser(SysUser user, String[] roleIds) {
        user.setCreateTime(new Date());
        user.setPassword(CommonUtil.MD5(user.getPassword()));
        //保存用户
        baseMapper.insert(user);



        //绑定角色
        bindRoles(user, roleIds);

    }
    @Override
    public void insertUserInfo(SysUser user, String[] roleIds,Integer[] postIds) {
        user.setPassword(CommonUtil.MD5(user.getPassword()));
        //保存用户
        baseMapper.insert(user);



        //绑定角色
        bindRoles(user, roleIds);
        //保存岗位
        bindPosts(user, postIds);
    }


    private void bindPosts(SysUser user, Integer[] postIds) {
        if (ArrayUtils.isNotEmpty(postIds)) {
            for (Integer pid : postIds) {
                PostUserInfo postUserInfo = new PostUserInfo();
                postUserInfo.setUserId(user.getId());
                postUserInfo.setPostId(pid);
                tbPostUserInfoService.insert(postUserInfo);
            }
        }
    }

    private void bindRoles(SysUser user, String[] roleIds) {
        if (ArrayUtils.isNotEmpty(roleIds)) {
            for (String rid : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(user.getId().toString());
                sysUserRole.setRoleId(rid);
                sysUserRoleService.insert(sysUserRole);
            }
        }
    }

    @Override
    public void updateUser(SysUser sysUser, String[] roleIds) {
        //sysUser.setPassword(null);
        SysUser sysUser1=this.selectById(sysUser.getId());



        //更新用户
        baseMapper.updateById(sysUser);

     if(roleIds!=null){
         //删除已有权限
         sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
         //重新绑定角色
         bindRoles(sysUser, roleIds);
     }


    }

    @Override
    public SysUser login(String userName, String password) {
        return this.selectOne(new EntityWrapper<SysUser>().eq("user_name", userName).eq("password", CommonUtil.MD5(password)).eq("is_open",0).eq("is_cancel",0).eq("is_reappoint",0).eq("is_delete","false"));
    }

    @Override
    public Page<UserCompany> selectUserList(Page<UserCompany> page, HashMap<String,Object> param) {
        page.setRecords(baseMapper.selectUserList(page,param));
        return page;
    }

    @Override
    public void delete(String id) {
        this.deleteById(id);
        sysUserRoleService.delete(new EntityWrapper<SysUserRole>().addFilter("user_id = {0}", id));
    }

    @Override
    public void updateUserInfo(SysUser sysUser, String[] roleId, Integer[] postId) {
        sysUser.setPassword(null);


        //更新用户
        baseMapper.updateById(sysUser);
        //删除已有权限
        sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
        tbPostUserInfoService.delete(new EntityWrapper<PostUserInfo>().eq("user_id", sysUser.getId()));
        //绑定角色
      bindRoles(sysUser, roleId);
        //保存岗位
        bindPosts(sysUser, postId);
    }
    @Override
    public List<SysUser> sysUserAllList(Integer unitId) {
        //查找当前用户所在机构及下属机构的所有用户
        return baseMapper.sysUserAllList(unitId);
    }

    @Override
    public UserCompany selectUserDetailById(Integer id) {
        return baseMapper.selectUserDetailById(id);
    }

    @Override
    public UserCompany selectLeadDetailById(Integer id) {
        return baseMapper.selectLeadDetailById(id);
    }
}