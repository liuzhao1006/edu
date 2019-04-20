package com.lz.toy.common.datatables;

/**
 * @author liuzhao
 */
public class TestModel {
    public String getUserName() {
        return userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String createTime;
}
