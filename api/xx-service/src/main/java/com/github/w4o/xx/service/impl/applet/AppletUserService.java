package com.github.w4o.xx.service.impl.applet;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.service.dto.applet.user.UserDTO;
import com.github.w4o.xx.service.mapper.AppletUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletUserService extends BaseServiceImpl<AppletUserMapper, AppletUserEntity> implements BaseService<AppletUserEntity> {

    /**
     * 获取分页列表
     *
     * @param page   分页参数
     * @param entity 请求参数
     * @return 分页列表
     */
    public Page<UserDTO> getPageList(Page<AppletUserEntity> page, AppletUserEntity entity) {
        return baseMapper.findPage(page, entity);
    }
}
