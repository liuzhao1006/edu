package com.lz.toy.entity.vo;

import com.lz.toy.entity.SysMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 菜单树
 * @author liuzhao
 */
@Getter
@Setter
@ToString
public class TreeMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单
	 */
	private SysMenu sysMenu;
	/**
	 * 子菜单
	 */
	private List<TreeMenu> children = new ArrayList<>();

}
