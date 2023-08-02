package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.manage.dto.sys.media.MediaDTO;
import com.github.w4o.xx.manage.param.sys.media.MediaPageParam;
import com.github.w4o.xx.manage.param.sys.media.MediaParam;

/**
 * 系统媒体管理服务接口
 *
 * @author Frank
 */
public interface SysMediaService extends BaseService<SysMediaEntity> {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<MediaDTO> getPageList(MediaPageParam param);

    /**
     * 修改媒体信息
     *
     * @param id    媒体id
     * @param param 请求参数
     */
    void update(Long id, MediaParam param);
}
