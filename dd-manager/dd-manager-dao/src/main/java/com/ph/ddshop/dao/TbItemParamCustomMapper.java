package com.ph.ddshop.dao;

import com.ph.ddshop.common.dto.Order;
import com.ph.ddshop.common.dto.Page;
import com.ph.ddshop.pojo.vo.TbItemParamCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemParamCustomMapper {

    int countitem();

    List<TbItemParamCustom> selectItemByPage(@Param("page") Page page, @Param("order") Order order);
}