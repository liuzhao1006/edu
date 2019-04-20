package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author liuzhao
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value="Id", type= IdType.AUTO)
	private Integer id;
	/**
	 * 用户名
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 密码
	 */

	private String password;
	/**
	 * 用户状态,0-启用,1-禁用
	 */
	@TableField("user_state")
	private Integer userState;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 描述
	 */
	@TableField("user_desc")
	private String userDesc;
	/**
	 * 头像
	 */
	@TableField("user_img")
	private String userImg;
	/**
	 * 部门主键
	 */
	@TableField("dept_id")
	private String deptId;
	/**
	 * 组织机构ID
	 * */
	@TableField("employee_id")
	private String employeeId;

	/**
	 *
	 * email
	 */
	private String email;

	/**
	 *
	 * 姓名
	 */
	@TableField("sys_user_name")
	private String sysUserName;
	/**
	 *
	 * 身份证号
	 */
	@TableField("id_card")
	private String idCard;
	/**
	 *
	 * 手机号
	 */
	@TableField("telephone")
	private String telephone;
	/**
	 *
	 * 住址
	 */
	private String address;
	/**
	 *
	 * 照片
	 */
	private String photo;
	/**
	 *
	 * 请假/销假
	 */
	@TableField("is_leave")
	private Boolean isLeave;
	/**
	 *
	 * 删除状态
	 */
	@TableField("is_delete")
	private Boolean isDelete  ;
	/**
	 *
	 * 离职/重聘
	 */
	@TableField("is_reappoint")
	private Boolean isReappoint;
	/**
	 *
	 * 注销/注销恢复
	 */
	@TableField("is_cancel")
	private Boolean isCancel;
	/**
	 *
	 * 账号启用/禁用
	 */
	@TableField("is_open")
	private Boolean isOpen;
	/**
	 *
	 * 所属单位
	 */
	@TableField("unit_id")
	private Integer unitId;
	/**
	 *
	 * 所属科室
	 */
	@TableField("depart_id")
	private Integer departId;
	/**
	 *
	 * 性别
	 */

	private String sex;
	/**
	 *
	 * 出生日期
	 */
	@TableField("birthday")
	private Date birthday;
	/**
	 *
	 * 文化程度
	 */
	private Integer education;
	/**
	 *
	 * 职务岗位
	 */
	@TableField("post_id")
	private Integer postId;
	/**
	 *
	 * 人员类型
	 */
	@TableField("user_type")
	private Integer userType;
	/**
	 *
	 * 入职时间
	 */
	@TableField("entry_date")
	private Date entryDate;

	/**
	 *
	 * 专业技术职称
	 */
	private String skill;
	/**
	 *
	 * 安全培训合格证
	 */
	private String contract;
	/**
	 *
	 * 合格证培训时间
	 */
	@TableField("train_date")
	private Date trainDate;
	/**
	 *
	 * 工号
	 */
	@TableField("job_number")
	private String jobNumber;
	/**
	 *
	 * 人员分类
	 */
	@TableField("person_type")
	private Integer personType;
	/**
	 *
	 * 离职辞退时间
	 */
	@TableField("leave_date")
	private Date leaveDate;
	/**
	 *
	 * 道路运输工作年限
	 */
	@TableField("transport_year")
	private String transportYear;
	/**
	 *
	 * 职称类别
	 */
	@TableField("duty_type")
	private String dutyType;
	/**
	 *
	 * 当年是否培训
	 */
	@TableField("is_train")
	private Boolean isTrain;
	/**
	 *
	 * 是否安委会/领导小组负责人
	 */
	@TableField("is_leader")
	private Boolean isLeader;
	/**
	 *
	 * 安全培训合格证附件
	 */
	private String certificate;

	/**
	 *
	 * 有效日期
	 */
	@TableField("valid_date")
	private Date validDate;
	@TableField("create_user")
	private String createUser;



	public String getEducationUserType() {
		return educationUserType;
	}

	public void setEducationUserType(String educationUserType) {
		this.educationUserType = educationUserType;
	}

	/**
	 * 学习教育用户类型
	 */

	@TableField("education_user_type")
	private String educationUserType;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
