package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.param.mall.goods.AddGoodsParam;
import com.github.w4o.xx.manage.param.mall.goods.GoodsPageParam;

import java.util.Map;

/**
 * @author Frank
 */
public interface MallGoodsService {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<Map<String, Object>> getPageList(GoodsPageParam param);

    /**
     * 添加商品
     *
     * @param param 请求参数
     */
    void add(AddGoodsParam param);

    /**
     * 修改商品
     *
     * @param id    商品id
     * @param param 请求参数
     */
    void update(long id, AddGoodsParam param);

    /**
     * 删除商品
     *
     * @param id 商品id
     */
    void delete(long id);

    /**
     * 修改商品状态
     *
     * @param id     商品id
     * @param status 状态
     */
    void updateStatus(long id, int status);
}
