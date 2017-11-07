package com.ph.ddshop.service.impl;

import com.ph.ddshop.dao.TbItemMapper;
import com.ph.ddshop.pojo.po.TbItem;
import com.ph.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: jack
 * Date: 2017/11/6
 * Time: 11:07
 * Version:V1.0
 */
@Service
public class ItemServiceimpl implements ItemService {

    @Autowired
    private TbItemMapper tbitemdao;
    @Override
    public TbItem findByItemId(Long itemid) {
        return tbitemdao.selectByPrimaryKey(itemid);
    }
}
