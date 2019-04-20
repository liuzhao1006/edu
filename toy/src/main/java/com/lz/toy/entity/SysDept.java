package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * <p>
 * 部门表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 部门名称
     */
	private String deptName;
    /**
     * 描述
     */
	private String deptDesc;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
