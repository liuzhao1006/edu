package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 菜单名称
     */
	private String menuName;
    /**
     * 父级菜单ID
     */
	private String pid;
    /**
     * 连接地址
     */
	private String url;
    /**
     * 图标
     */
	private String icon;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 深度
     */
	private Integer deep;
    /**
     * 编码
     */
	private String code;
    /**
     * 资源名称
     */
	private String resource;
	/**
	 * 是否有数据权限
	 */
	private int powers;
	/**
	 * 父级菜单名称
	 */
	@TableField(exist = false)
	private String menuParentName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
