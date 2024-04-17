package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.manage.dto.applet.user.UserDTO;
import com.github.w4o.xx.manage.param.applet.user.UserPageParam;

/**
 * 小程序用户表 Mapper 接口
 *
 * @author Frank
 */
public interface AppletUserMapper extends BaseMapper<AppletUserEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<UserDTO> findPage(Page<?> page, UserPageParam param);
}
