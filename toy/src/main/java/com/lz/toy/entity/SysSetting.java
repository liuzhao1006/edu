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
 * 系统设置表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_setting")
public class SysSetting extends Model<SysSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String Id;
    /**
     * KEY
     */
	private String sysKey;
    /**
     * 名称
     */
	private String sysName;
    /**
     * 值
     */
	private String sysValue;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 说明
     */
	private String sysDesc;

	@Override
	protected Serializable pkVal() {
		return this.Id;
	}

}
