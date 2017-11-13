package com.ph.ddshop.service.impl;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.dao.TbItemParamCustomMapper;
import com.ph.ddshop.pojo.vo.TbItemParamCustom;
import com.ph.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/13
 * Time: 21:04
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemParamCustomMapper tbItemParamCustomdao;



    @Override
    public Result<TbItemParamCustom> listItemParam(Page page, Order order) {
        Result<TbItemParamCustom> result = null;
        try {
            result = new Result<TbItemParamCustom>();
             int total = tbItemParamCustomdao.countitem();
                result.setTotal(total);
                List<TbItemParamCustom> list= tbItemParamCustomdao.selectItemByPage(page,order);
                result.setRows(list);
            }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return result;
    }

}
