package com.lz.toy.serivce;

import com.baomidou.mybatisplus.service.IService;
import com.lz.toy.entity.SysUserRole;


/**
 *
 * SysUserRole 表数据服务层接口
 * @author liuzhao
 *
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    SysUserRole getSysUserRole(String userId);
}