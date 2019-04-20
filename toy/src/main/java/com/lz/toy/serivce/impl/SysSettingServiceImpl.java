package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lz.toy.entity.SysSetting;
import com.lz.toy.mapper.SysSettingMapper;
import com.lz.toy.serivce.ISysSettingService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * SysSetting 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {
	
	@Cacheable(value = "settingCache")
	@Override
	public List<SysSetting> findAll() {
		return this.selectList(new EntityWrapper<SysSetting>().orderBy("sort",true));
	}


}