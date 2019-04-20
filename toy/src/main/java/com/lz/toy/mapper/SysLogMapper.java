package com.lz.toy.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lz.toy.entity.SysLog;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
  * 日志表 Mapper 接口
 * </p>
 * @author liuzhao
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    List<SysLog> selectLogList(Page<SysLog> page, HashMap<String, Object> param);
}