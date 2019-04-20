package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_data_permissions")
public class TbDataPermissions extends Model<TbDataPermissions> {
    /**
     * 主键
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

    private String roleId;

    private String functionId;

    private Integer dataAccessScope;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
