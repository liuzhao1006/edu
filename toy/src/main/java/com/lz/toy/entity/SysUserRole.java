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
 * 用户角色关联表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String Id;
    /**
     * 用户主键
     */
	private String userId;
    /**
     * 角色主键
     */
	private String roleId;

	@Override
	protected Serializable pkVal() {
		return this.Id;
	}

}
