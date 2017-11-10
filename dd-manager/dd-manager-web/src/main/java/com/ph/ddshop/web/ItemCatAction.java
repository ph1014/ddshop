package com.ph.ddshop.web;

import com.ph.ddshop.common.dto.TreeNode;
import com.ph.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/10
 * Time: 19:15
 * Version:V1.0
 */
@Controller
@Scope("prototype")
public class ItemCatAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/itemCat")
    @ResponseBody
    public List<TreeNode> listItemCatsByPid(@RequestParam("parentid") Long parentid){

        List<TreeNode> list =null;
        try{
          list =   itemCatService.listItemCatsByPid(parentid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;

    }
}
