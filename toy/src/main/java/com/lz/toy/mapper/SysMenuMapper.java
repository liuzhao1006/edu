package com.lz.toy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lz.toy.entity.SysMenu;


import java.util.List;


/**
 *
 * SysMenu 表数据库控制层接口
 *@author liuzhao
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<String> selectMenuIdsByUserId(String uid);

}