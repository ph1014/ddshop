package com.ph.ddshop.web;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.pojo.po.TbItemParam;
import com.ph.ddshop.pojo.vo.TbItemParamCustom;
import com.ph.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: jack
 * Date: 2017/11/13
 * Time: 20:58
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class itemParamAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemParamService itemParamService;



    @RequestMapping("/itemParams")
    @ResponseBody
    public Result<TbItemParamCustom> doindex2(Page page, Order order) {
        Result<TbItemParamCustom> result = null;
        try {
            result= itemParamService.listItemParam(page,order);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return result;
    }
    @RequestMapping("/itemparamsave")
    @ResponseBody
    public int dosave(TbItemParam tbItemParam){
        int i =0;
        try {
            i=itemParamService.saveItemParam(tbItemParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "item/param/save/{cid}",method = RequestMethod.GET)
    public TbItemParam getTbItemParam(@PathVariable("cid")Long cid)
    {
        TbItemParam tbItemParam=null;
        try {
            tbItemParam=itemParamService.getTbItemParamByCid(cid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbItemParam;
    }



}
