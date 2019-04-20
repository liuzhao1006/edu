package com.lz.toy.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author liuzhao
 */


@Getter
@Setter
public class BusRouteInfoTree {

    private List<BusRouteInfoTree> children;
    private String id;
    private boolean open;
    private String pId;
    private String name;

    private boolean isParent;

    private String title;

    private int flag;

    private String icon;
    private Integer type;

}
