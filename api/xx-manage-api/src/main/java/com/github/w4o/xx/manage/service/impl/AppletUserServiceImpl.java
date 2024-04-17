package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.manage.dto.applet.user.UserDTO;
import com.github.w4o.xx.manage.mapper.AppletUserMapper;
import com.github.w4o.xx.manage.param.applet.user.UserPageParam;
import com.github.w4o.xx.manage.service.AppletUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序用户服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletUserServiceImpl extends BaseServiceImpl<AppletUserMapper, AppletUserEntity> implements AppletUserService {

    @Override
    public Page<UserDTO> getPageList(UserPageParam param) {
        return baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
    }
}
