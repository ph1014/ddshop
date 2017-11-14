package com.ph.ddshop.service.impl;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.common.unit.IDUtils;
import com.ph.ddshop.dao.TbItemDescMapper;
import com.ph.ddshop.dao.TbItemMapper;
import com.ph.ddshop.dao.TbItemMapperCustom;
import com.ph.ddshop.dao.TbItemParamItemMapper;
import com.ph.ddshop.pojo.po.*;
import com.ph.ddshop.pojo.vo.TbItemQuery;
import com.ph.ddshop.pojo.vo.TbitemCustom;
import com.ph.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: jack
 * Date: 2017/11/6
 * Time: 11:07
 * Version:V1.0
 */
@Service
public class ItemServiceimpl implements ItemService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper tbitemdao;
    @Autowired
    private TbItemMapperCustom customdao;
    @Autowired
    private TbItemDescMapper tbItemDessdao;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemdao;


    @Override
    public TbItem findByItemId(Long itemid) {
        return tbitemdao.selectByPrimaryKey(itemid);
    }

    @Override
    public Result<TbitemCustom> selectItemsBypage(Page page,Order order,TbItemQuery tbItemQuery) {
        Result<TbitemCustom> result = null;
        try {
            result = new Result<TbitemCustom>();
            int total = customdao.countitem(tbItemQuery);
            result.setTotal(total);
            List<TbitemCustom> list= customdao.selectItemByPage(page,order,tbItemQuery);
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

    @Override
    @Transactional
    public int saveItem(TbItem tbItem, String content,String paramData) {
        int i = 0;
        try {
            Long itemId = IDUtils.getItemId();

            tbItem.setId(itemId);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            tbItem.setStatus((byte)2);
            i = tbitemdao.insert(tbItem);

            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i += tbItemDessdao.insert(desc);

            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setUpdated(new Date());

            i += tbItemParamItemdao.insert(tbItemParamItem);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}
