package com.lz.toy.common.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户登录Token
 *  @author liuzhao
 */
@Getter
@Setter
public class Token implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String uid;
	/**
	 * 用户名
	 */
	private String uname;

	/**
	 * 数据
	 */
	private String data;

	/**
	 * 组织机构ID
	 */
	private String organize_id;

	/**
	 * 用户类型 1：监管 2：企业 3：超级管理员
	 */
	private Integer user_type;
	/**
	 * 原始密码（安全教育登录）
	 */
	private String originalPassword;
}
