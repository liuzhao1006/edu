package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 日志表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 用户
     */
	@TableField("user_name")
	private String userName;
    /**
     * 日志
     */
	private String title;
    /**
     * 地址
     */
	private String url;
    /**
     * 参数
     */
	private String params;
    /**
     * 日志时间
     */
	@TableField("create_time")
	private Date createTime;
	@TableField(exist = false)
	private String startDate;
	@TableField(exist = false)
	private String endDate;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
