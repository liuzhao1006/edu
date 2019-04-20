package com.lz.toy.entity.vo;

import com.lz.toy.entity.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 菜单树+是否有权限表示
 * @author liuzhao
 */
@Getter
@Setter
public class TreeMenuAllowAccess implements Serializable{

	/**
	* @Fields serialVersionUID : TODO()
	*/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单
	 */
	private SysMenu sysMenu;
	/**
	 * 是否允许访问
	 */
	private boolean allowAccess = false;
	/**
	 * 子菜单
	 */
	private List<TreeMenuAllowAccess> children = new ArrayList<>();
	

	
}
