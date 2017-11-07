package com.ph.ddshop.dao;

import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.pojo.vo.TbitemCustom;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/7
 * Time: 16:02
 * Version:V1.0
 */
public interface TbItemMapperCustom {
    int countitem();

    List<TbitemCustom> selectItemByPage(Page page);
}
