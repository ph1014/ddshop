package com.ph.ddshop.service.impl;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.dao.TbItemMapper;
import com.ph.ddshop.dao.TbItemMapperCustom;
import com.ph.ddshop.pojo.po.TbItem;
import com.ph.ddshop.pojo.po.TbItemExample;
import com.ph.ddshop.pojo.vo.TbitemCustom;
import com.ph.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private TbItemMapperCustom customdao;
    @Override
    public TbItem findByItemId(Long itemid) {
        return tbitemdao.selectByPrimaryKey(itemid);
    }

    @Override
    public Result<TbitemCustom> selectItemsBypage(Page page,Order order) {
        Result<TbitemCustom> result = null;
        try {
            result = new Result<TbitemCustom>();
            int total = customdao.countitem();
            result.setTotal(total);
            List<TbitemCustom> list= customdao.selectItemByPage(page,order);
            result.setRows(list);
        }catch (Exception e){

        }
             return  result;

    }

    @Override
    public int updateItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbitemdao.updateByExampleSelective(record,example);
    }

    @Override
    public int updateItemsByUp(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)1);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbitemdao.updateByExampleSelective(record,example);
    }

    @Override
    public int updateItemsByDown(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)2);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return tbitemdao.updateByExampleSelective(record,example);
    }
}
