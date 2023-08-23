package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.manage.dto.applet.banner.BannerDTO;
import com.github.w4o.xx.manage.param.applet.banner.BannerPageParam;
import com.github.w4o.xx.manage.param.applet.banner.BannerParam;

/**
 * 小程序轮播图服务接口
 *
 * @author Frank
 */
public interface AppletBannerService extends BaseService<AppletBannerEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<BannerDTO> getPageList(BannerPageParam param);

    /**
     * 添加轮播图
     *
     * @param param 请求参数
     */
    void add(BannerParam param);

    /**
     * 修改轮播图
     *
     * @param id    轮播图id
     * @param param 请求参数
     */
    void update(long id, BannerParam param);

    /**
     * 删除轮播图
     *
     * @param id 轮播图id
     */
    void delete(long id);

    /**
     * 更新轮播图是否显示
     *
     * @param id      轮播图id
     * @param visible 是否显示
     */
    void updateVisible(long id, boolean visible);
}
