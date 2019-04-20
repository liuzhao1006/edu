package com.lz.toy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lz.toy.entity.TbDataPermissions;
import com.lz.toy.entity.vo.RoleDataMenu;


import java.util.List;

/**
 * @author liuzhao
 */
public interface TbDataPermissionsMapper extends BaseMapper<TbDataPermissions> {
    List<RoleDataMenu> selectRoleDataList(String s);
    List<RoleDataMenu> selectRoleDataInfo(String roleId);
}
