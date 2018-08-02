package com.bkcell.security.common.entity;

import java.io.Serializable;
import java.util.List;

public class GridPage<T> implements Serializable {

    private long totalRows;
    private List<T> data;

    public GridPage(long totalRows, List<T> data) {
        this.totalRows = totalRows;
        this.data = data;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}