package com.ph.ddshop.service.impl;

import com.ph.ddshop.common.dto.TreeNode;
import com.ph.ddshop.dao.TbItemCatMapper;
import com.ph.ddshop.pojo.po.TbItemCat;
import com.ph.ddshop.pojo.po.TbItemCatExample;
import com.ph.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jack
 * Date: 2017/11/10
 * Time: 19:25
 * Version:V1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper tbItemCatdao;

    @Override
    public List<TreeNode> listItemCatsByPid(Long parentid) {
        List<TreeNode> list = null;
        try {
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criterion = example.createCriteria();
            criterion.andParentIdEqualTo(parentid);
            List<TbItemCat> tbItemCats = tbItemCatdao.selectByExample(example);
            list=new ArrayList<>();

            for(int i=0;i<tbItemCats.size();i++){
                TreeNode treeNode = new TreeNode();
                TbItemCat tbItemCat = tbItemCats.get(i);
                treeNode.setId(tbItemCat.getId());
                treeNode.setText(tbItemCat.getName());
                treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
                list.add(treeNode);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }
}
