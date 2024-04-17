package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.manage.dto.applet.user.UserDTO;
import com.github.w4o.xx.manage.param.applet.user.UserPageParam;

/**
 * @author Frank
 */
public interface AppletUserService extends BaseService<AppletUserEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<UserDTO> getPageList(UserPageParam param);
}
