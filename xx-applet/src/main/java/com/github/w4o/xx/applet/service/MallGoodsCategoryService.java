package com.github.w4o.xx.applet.service;

import com.github.w4o.xx.applet.dto.mall.GoodsCategoryDTO;

import java.util.List;

/**
 * @author Frank
 */
public interface MallGoodsCategoryService {

    /**
     * 商品分类列表
     *
     * @return 商品分类列表
     */
    List<GoodsCategoryDTO> list();
}
