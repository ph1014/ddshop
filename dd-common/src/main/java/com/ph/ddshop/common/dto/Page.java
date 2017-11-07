package com.ph.ddshop.common.dto;

/**
 * User: jack
 * Date: 2017/11/7
 * Time: 16:06
 * Version:V1.0
 */
public class Page {
    private int page;
    private int rows;

    public int getOffset(){
        return (page-1)*rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
