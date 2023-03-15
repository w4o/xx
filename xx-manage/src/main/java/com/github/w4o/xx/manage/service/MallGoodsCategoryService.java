package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.param.mall.category.AddGoodsCategoryParam;
import com.github.w4o.xx.manage.param.mall.category.GoodsCategoryPageParam;

import java.util.Map;

/**
 * @author Frank
 */
public interface MallGoodsCategoryService {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<Map<String, Object>> getPageList(GoodsCategoryPageParam param);

    /**
     * 添加商品分类
     *
     * @param param 请求参数
     */
    void add(AddGoodsCategoryParam param);

    /**
     * 修改商品分类
     *
     * @param id    商品分类id
     * @param param 请求参数
     */
    void update(long id, AddGoodsCategoryParam param);

    /**
     * 删除商品分类
     *
     * @param id 商品分类id
     */
    void delete(long id);

    /**
     * 启用商品分类
     *
     * @param id 商品分类id
     */
    void enable(long id);

    /**
     * 禁用商品分类
     *
     * @param id 商品分类id
     */
    void disable(long id);
}
