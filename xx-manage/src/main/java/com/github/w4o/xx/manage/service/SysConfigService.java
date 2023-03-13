package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.dto.sys.config.SysConfigPageDTO;
import com.github.w4o.xx.manage.param.sys.config.AddConfigParam;
import com.github.w4o.xx.manage.param.sys.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.sys.config.ModifyConfigParam;

/**
 * 系统配置服务接口
 *
 * @author Frank
 */
public interface SysConfigService {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<SysConfigPageDTO> getPageList(ConfigPageParam param);

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
