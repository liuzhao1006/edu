package com.lz.toy.common.datatables;

/**
 * @author liuzhao
 */
public class OrderColumn {
    private String orderColumn;

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    private String orderDir;
    public String getOrderColumn() {
        return orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }
}
