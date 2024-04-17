package com.github.w4o.xx.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.w4o.xx.applet.param.AppletBindPhoneParam;
import com.github.w4o.xx.applet.param.UpdateAvatarParam;
import com.github.w4o.xx.applet.param.UpdateNicknameParam;
import com.github.w4o.xx.core.entity.AppletUserEntity;

/**
 * @author Frank
 */
public interface AppletUserService extends IService<AppletUserEntity> {

    /**
     * 修改昵称
     *
     * @param param 修改昵称请求参数
     */
    void updateNickname(UpdateNicknameParam param);

    /**
     * 修改头像
     *
     * @param param 修改头像请求参数
     */
    void updateAvatar(UpdateAvatarParam param);

    /**
     * 绑定手机号
     *
     * @param param 绑定手机号请求参数
     */
    void bindPhone(AppletBindPhoneParam param);
}
