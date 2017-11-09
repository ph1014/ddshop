package com.ph.ddshop.web;


import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.common.dto.Result;
import com.ph.ddshop.pojo.po.TbItem;
import com.ph.ddshop.pojo.vo.TbItemQuery;
import com.ph.ddshop.pojo.vo.TbitemCustom;
import com.ph.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/6
 * Time: 10:44
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class itemAction {

    @Autowired
    private ItemService itemservice;


    @RequestMapping(value ="/item/{itmeid}",method = RequestMethod.GET)
    @ResponseBody
    public TbItem findById(@PathVariable("itmeid") Long itemid){

        return itemservice.findByItemId(itemid);

    }
    @RequestMapping("/")
    public String doindex(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String doindex1(@PathVariable("page") String page){
        return page;
    }


    @RequestMapping("/items")
    @ResponseBody
    public Result<TbitemCustom> doindex2(Page page, Order order, TbItemQuery tbItemQuery){
        System.out.println(page.getPage()+"---"+page.getRows());
        Result<TbitemCustom> list = itemservice.selectItemsBypage(page,order,tbItemQuery);
        return list;
    }

    @RequestMapping(value = "/items/batch",method = RequestMethod.POST)
    @ResponseBody
    public int doindex3(@RequestParam ("ids[]") List<Long> ids){

            return itemservice.updateItemsByIds(ids);
    }

    @RequestMapping(value = "items/up",method = RequestMethod.POST)
    @ResponseBody
    public int doindex4(@RequestParam ("ids[]") List<Long> ids){

        return itemservice.updateItemsByUp(ids);
    }
    @RequestMapping(value = "items/down",method = RequestMethod.POST)
    @ResponseBody
    public int doindex5(@RequestParam ("ids[]") List<Long> ids){

        return itemservice.updateItemsByDown(ids);
    }


}
