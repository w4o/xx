package com.github.w4o.xx.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.w4o.xx.applet.common.util.LoginUtils;
import com.github.w4o.xx.applet.mapper.AppletUserMapper;
import com.github.w4o.xx.applet.param.AppletBindPhoneParam;
import com.github.w4o.xx.applet.param.UpdateAvatarParam;
import com.github.w4o.xx.applet.param.UpdateNicknameParam;
import com.github.w4o.xx.applet.service.AppletUserService;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.core.util.AssertUtils;
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
public class AppletUserServiceImpl extends ServiceImpl<AppletUserMapper, AppletUserEntity> implements AppletUserService {

    @Override
    public void updateNickname(UpdateNicknameParam param) {
        AppletUserEntity entity = baseMapper.selectById(LoginUtils.getLoginId());
        AssertUtils.notNull(entity);
        entity.setNickname(param.getNickname());
        baseMapper.updateById(entity);
    }

    @Override
    public void updateAvatar(UpdateAvatarParam param) {
        AppletUserEntity entity = baseMapper.selectById(LoginUtils.getLoginId());
        AssertUtils.notNull(entity);
        entity.setHeadImgUrl(param.getAvatar());
        baseMapper.updateById(entity);
    }

    @Override
    public void bindPhone(AppletBindPhoneParam param) {
        AppletUserEntity entity = baseMapper.selectById(LoginUtils.getLoginId());
        AssertUtils.notNull(entity);
        entity.setMobile(param.getPhone());
        baseMapper.updateById(entity);
    }
}
