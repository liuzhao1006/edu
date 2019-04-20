package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 角色名称
     */
	private String roleName;
    /**
     * 角色描述
     */
	private String roleDesc;
    /**
     * 状态,1-启用,-1禁用
     */
	private Integer roleState;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 创建用户
     */
	private Integer createUser;
    /**
     * 所属企业
     */
	private Integer groupId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
