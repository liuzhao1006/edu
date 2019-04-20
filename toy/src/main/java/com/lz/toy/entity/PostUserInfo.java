package com.lz.toy.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liuzhao
 */
@Getter
@Setter
@TableName("tb_post_user_info")
public class PostUserInfo extends Model<PostUserInfo> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type= IdType.UUID)
    private String Id;
    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 岗位主键
     */
    private Integer postId;

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }
}
