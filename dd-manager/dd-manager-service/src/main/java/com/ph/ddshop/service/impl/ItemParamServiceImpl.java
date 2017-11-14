package com.ph.ddshop.service.impl;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.dao.TbItemParamCustomMapper;
import com.ph.ddshop.dao.TbItemParamMapper;
import com.ph.ddshop.pojo.po.TbItemParam;
import com.ph.ddshop.pojo.po.TbItemParamExample;
import com.ph.ddshop.pojo.vo.TbItemParamCustom;
import com.ph.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private TbItemParamMapper tbItemParamdao;


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

    @Override
    public TbItemParam getTbItemParamByCid(Long cid) {
        TbItemParam tbItemParam=null;
        try {
            TbItemParamExample example=new TbItemParamExample();
            TbItemParamExample.Criteria  criteria= example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);

            List<TbItemParam> tbItemParams = tbItemParamdao.selectByExampleWithBLOBs(example);
            if(tbItemParams!=null){
                tbItemParam=tbItemParams.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbItemParam;
    }

    @Override
    public int saveItemParam(TbItemParam tbItemParam) {
        int i =0;
        try {
            tbItemParam.setCreated(new Date());
            tbItemParam.setUpdated(new Date());
            i=tbItemParamdao.insert(tbItemParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

}
