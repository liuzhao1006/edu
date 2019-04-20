package com.lz.toy.ueditor.define;

/**
 * 处理状态接口
 *
 * @author liuzhao
 */
public interface State {

    boolean isSuccess();

    void putInfo(String name, String val);

    void putInfo(String name, long val);

    String toJSONString();

}
