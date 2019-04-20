package com.lz.toy.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liuzhao
 */

@Setter
@Getter
@ToString
public class RoleDataMenu {


    private String id;

    private String functionId;

    private String roleId;

    private Integer dataAccessScope;


}
