package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lz.toy.entity.SysUserRole;
import com.lz.toy.mapper.SysUserRoleMapper;
import com.lz.toy.serivce.ISysUserRoleService;
import org.springframework.stereotype.Service;


/**
 *
 * SysUserRole 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public SysUserRole getSysUserRole(String userId) {
        EntityWrapper<SysUserRole> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id",userId);
        return this.selectOne(entityWrapper);
    }
}