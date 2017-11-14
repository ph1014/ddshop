package com.ph.ddshop.service;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.pojo.po.TbItemParam;
import com.ph.ddshop.pojo.vo.TbItemParamCustom;

/**
 * User: jack
 * Date: 2017/11/13
 * Time: 21:04
 * Version:V1.0
 */

public interface ItemParamService {
    Result<TbItemParamCustom> listItemParam(Page page, Order order);

    int saveItemParam(TbItemParam tbItemParam);

    TbItemParam getTbItemParamByCid(Long cid);
}
