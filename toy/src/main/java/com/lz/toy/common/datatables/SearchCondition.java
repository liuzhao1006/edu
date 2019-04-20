package com.lz.toy.common.datatables;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SearchCondition<T> {

    private T condition;
    private int pageSize;
    private int startIndex;
    private int endIndex;
    private String draw;
    private List<OrderColumn> orderColumns;
    private boolean fuzzySearch;
    private String fuzzy;
    private String menuId;

    public int getEndIndex() {
        return startIndex * pageSize;
    }
}
