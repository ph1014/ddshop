package com.ph.ddshop.service;

import com.ph.ddshop.pojo.po.TbItem;

/**
 * User: jack
 * Date: 2017/11/6
 * Time: 11:04
 * Version:V1.0
 */

public interface ItemService {
    TbItem findByItemId(Long itemid);
}
