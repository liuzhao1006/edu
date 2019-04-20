package com.lz.toy.serivce;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lz.toy.entity.SysLog;

import java.util.HashMap;


/**
 *
 * SysLog 表数据服务层接口
 * @author liuzhao
 *
 */
public interface ISysLogService extends IService<SysLog> {

	/**
	 * 记录日志
	 * @param title 标题
	 * @param uname 用户名
	 * @param url 地址
	 * @param parms 参数
	 */
	void insertLog(String title, String uname, String url, String parms);

	Page<SysLog> selectLogList(Page<SysLog> page, HashMap<String, Object> param);
}