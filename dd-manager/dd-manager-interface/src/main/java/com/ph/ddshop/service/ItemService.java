package com.ph.ddshop.service;

import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.pojo.po.TbItem;
import com.ph.ddshop.pojo.vo.TbitemCustom;

/**
 * User: jack
 * Date: 2017/11/6
 * Time: 11:04
 * Version:V1.0
 */

public interface ItemService {
    TbItem findByItemId(Long itemid);


    Result<TbitemCustom> selectItemsBypage(Page page);
}
