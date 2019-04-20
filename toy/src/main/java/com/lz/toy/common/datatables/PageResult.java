package com.lz.toy.common.datatables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class PageResult<T> {
    public PageResult(List<T> data,int count,String search) {
        this.aaData = data;
        this.iTotalDisplayRecords =count;
        this.iTotalRecords =count;
        this.sEcho = Integer.parseInt(search)+1;
    }

    private List<T> aaData;
    private int iTotalDisplayRecords;
    private int iTotalRecords;
    private int sEcho;
}
