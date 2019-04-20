package com.lz.toy.serivce;



import com.baomidou.mybatisplus.service.IService;
import com.lz.toy.entity.SysSetting;

import java.util.List;


/**
 *
 * SysSetting 表数据服务层接口
 * @author liuzhao
 *
 */
public interface ISysSettingService extends IService<SysSetting> {

	List<SysSetting> findAll();


}