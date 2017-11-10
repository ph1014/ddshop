package com.ph.ddshop.service;

import com.ph.ddshop.common.dto.TreeNode;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/10
 * Time: 19:24
 * Version:V1.0
 */
public interface ItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentid);
}
