package com.lz.toy.treetable;


import com.lz.toy.entity.vo.BusRouteInfoTree;

import java.util.ArrayList;
import java.util.List;

public class TreeList {
    private List<BusRouteInfoTree> resultNodes = new ArrayList<BusRouteInfoTree>();//树形结构排序之后list内容
    private List<BusRouteInfoTree> nodes; //传入list参数
    public TreeList(List<BusRouteInfoTree> nodes) {//通过构造函数初始化
        this.nodes = nodes;
    }

    /**
     * 构建树形结构list
     * @return 返回树形结构List列表
     */
    public List<BusRouteInfoTree> buildTree() {
        for (BusRouteInfoTree node : nodes) {
            Integer id =Integer.parseInt(node.getId()) ;
            if (node.getPId().equals("0")) {//通过循环一级节点 就可以通过递归获取二级以下节点

                build(node);//递归获取二级、三级、。。。节点
                resultNodes.add(node);//添加一级节点
          }
        }
        return resultNodes;
    }
    /**
     * 构建树形结构list
     * @return 返回树形结构List列表
     */
    public List<BusRouteInfoTree> buildParentTree(Integer parentId) {
        for (BusRouteInfoTree node : nodes) {
            if (node.getPId().equals(parentId.toString())) {//通过循环一级节点 就可以通过递归获取二级以下节点

                build(node);//递归获取二级、三级、。。。节点
                resultNodes.add(node);//添加一级节点
            }
            if (node.getPId().equals("0")) {//通过循环一级节点 就可以通过递归获取二级以下节点

                build(node);//递归获取二级、三级、。。。节点
                resultNodes.add(node);//添加一级节点
            }

        }
        return resultNodes;
    }
    /**
     * 递归循环子节点
     *
     * @param node 当前节点
     */
    private void build(BusRouteInfoTree node) {
        List<BusRouteInfoTree> children = getChildren(node);
        if (!children.isEmpty()) {//如果存在子节点
            for (BusRouteInfoTree child : children) {//将子节点遍历加入返回值中
                node.getChildren().add(child);
                build(child);
            }
        }
    }
    /**
     * @param node
     * @return 返回
     */
    private List<BusRouteInfoTree> getChildren(BusRouteInfoTree node) {
        List<BusRouteInfoTree> children = new ArrayList<BusRouteInfoTree>();
        Integer id = Integer.parseInt(node.getId()) ;
        for (BusRouteInfoTree child : nodes) {
            if (id==Integer.parseInt(child.getPId())) {//如果id等于父id
                children.add(child);//将该节点加入循环列表中
            }
        }
        return children;
    }



}
