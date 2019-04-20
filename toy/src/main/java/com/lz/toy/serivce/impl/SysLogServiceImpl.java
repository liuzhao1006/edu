package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lz.toy.entity.SysLog;
import com.lz.toy.mapper.SysLogMapper;
import com.lz.toy.serivce.ISysLogService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;


/**
 *
 * SysLog 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Log4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Override
	public void insertLog(String title, String uname, String url, String parms) {
		SysLog sysLog  =new SysLog();
		sysLog.setCreateTime(new Date());
		sysLog.setTitle(title);
		sysLog.setUserName(uname);
		sysLog.setUrl(url);
		sysLog.setParams(parms);
		super.insert(sysLog);
		log.debug("记录日志:"+sysLog.toString());
	}

	@Override
	public Page<SysLog> selectLogList(Page<SysLog> page, HashMap<String, Object> param) {
        page.setRecords(baseMapper.selectLogList(page,param));
		return page;
	}
}