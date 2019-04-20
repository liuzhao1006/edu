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
 * 角色菜单关联表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 角色主键
     */
	private String roleId;
    /**
     * 菜单主键
     */
	private String menuId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
