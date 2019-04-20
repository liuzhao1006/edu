package com.lz.toy.entity.vo;

import com.lz.toy.entity.SysMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class SysMenus implements Serializable{

    /**
     * 菜单
     */
    private SysMenu sysMenu;
    /**
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<>();
}
