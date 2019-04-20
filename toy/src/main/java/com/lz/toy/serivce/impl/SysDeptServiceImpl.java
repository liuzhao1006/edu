package com.lz.toy.serivce.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.lz.toy.entity.SysDept;
import com.lz.toy.mapper.SysDeptMapper;
import com.lz.toy.serivce.ISysDeptService;
import org.springframework.stereotype.Service;


/**
 *
 * SysDept 表数据服务层接口实现类
 * @author liuzhao
 *
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

}