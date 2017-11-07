package com.ph.ddshop.web;


import com.ph.ddshop.pojo.po.TbItem;
import com.ph.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
