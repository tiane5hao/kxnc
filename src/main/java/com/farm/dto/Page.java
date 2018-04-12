package com.farm.dto;

import java.io.Serializable;

public class Page implements Serializable{

    private Integer start;

    private Integer rows;

    private Integer page;

    private Integer pageCount;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void initStart(){
        this.start = (this.page - 1) * this.rows;
    }
}
