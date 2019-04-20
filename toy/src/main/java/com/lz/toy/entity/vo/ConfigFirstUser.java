package com.lz.toy.entity.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liuzhao
 */

@Getter
@Setter
@ToString
public class ConfigFirstUser {


    /**
     * id
     */
    private int id;

    /**
     * 参数名
     */
    private String first_name;
    /**
     * 标识
     */
    private String first_mark;

    /**
     * 创建人
     */
    private String user_name;

    /**
     * 操作时间
     */
    private Date i_date;

}
