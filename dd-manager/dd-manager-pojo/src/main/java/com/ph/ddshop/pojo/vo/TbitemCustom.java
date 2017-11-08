package com.ph.ddshop.pojo.vo;

import com.ph.ddshop.pojo.po.TbItem;

/**
 * User: jack
 * Date: 2017/11/7
 * Time: 15:57
 * Version:V1.0
 */
public class TbitemCustom extends TbItem{
    private String catname;
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
