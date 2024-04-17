package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import com.github.w4o.xx.manage.dto.applet.config.ConfigDTO;
import com.github.w4o.xx.manage.param.applet.config.AddConfigParam;
import com.github.w4o.xx.manage.param.applet.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.applet.config.ModifyConfigParam;

/**
 * @author Frank
 */
public interface AppletConfigService extends BaseService<AppletConfigEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<ConfigDTO> getPageList(ConfigPageParam param);

    /**
     * 添加配置
     *
     * @param param 请求参数
     */
    void add(AddConfigParam param);

    /**
     * 修改配置
     *
     * @param id    配置id
     * @param param 请求参数
     */
    void update(long id, ModifyConfigParam param);

    /**
     * 删除配置
     *
     * @param id 配置id
     */
    void delete(long id);
}
