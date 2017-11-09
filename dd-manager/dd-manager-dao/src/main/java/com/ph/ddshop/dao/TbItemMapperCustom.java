package com.ph.ddshop.dao;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.pojo.vo.TbItemQuery;
import com.ph.ddshop.pojo.vo.TbitemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: jack
 * Date: 2017/11/7
 * Time: 16:02
 * Version:V1.0
 */
public interface TbItemMapperCustom {
    int countitem(@Param("tbItemQuery")TbItemQuery tbItemQuery);

    List<TbitemCustom> selectItemByPage(@Param("page")Page page,@Param("order")Order order,@Param("tbItemQuery")TbItemQuery tbItemQuery);
}
