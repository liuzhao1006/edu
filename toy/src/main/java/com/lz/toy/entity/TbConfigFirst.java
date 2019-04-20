package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuzhao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_config_first")
public class TbConfigFirst extends Model<TbConfigFirst> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String firstName;
    /**
     * 父ID
     */
	private Integer parentId;
    /**
     * 标识
     */
	private String firstMark;
    /**
     * 创建人
     */
	private String userId;
    /**
     * 操作时间
     */
	private Date IDate;
    /**
     * 删除标识
     */
	private Boolean deleteMark;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
