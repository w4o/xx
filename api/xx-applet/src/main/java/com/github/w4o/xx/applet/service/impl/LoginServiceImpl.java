package com.github.w4o.xx.applet.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.hutool.core.util.DesensitizedUtil;
import com.github.w4o.xx.applet.common.LoginUser;
import com.github.w4o.xx.applet.common.util.JwtUtils;
import com.github.w4o.xx.applet.common.util.LoginUtils;
import com.github.w4o.xx.applet.mapper.AppletUserMapper;
import com.github.w4o.xx.applet.param.LoginParam;
import com.github.w4o.xx.applet.service.LoginService;
import com.github.w4o.xx.applet.vo.LoginVO;
import com.github.w4o.xx.applet.vo.UserInfoVO;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {

    private final WxMaService wxMaService;
    private final AppletUserMapper appletUserMapper;
    private final JwtUtils jwtUtils;

    private WxMaJscode2SessionResult getSession(String code) {
        try {
            return wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            log.error("小程序登陆逻辑异常", e);
            throw new CustomException(ErrorCode.E1000, e.getMessage());
        }
    }

    private String getPhoneNumber(String code) {
        WxMaPhoneNumberInfo wxMaPhoneNumberInfo;
        // 获取用户手机号
        try {
            wxMaPhoneNumberInfo = wxMaService.getUserService().getPhoneNoInfo(code);
        } catch (WxErrorException e) {
            log.error("获取用户手机号异常", e);
            return null;
        }
        return wxMaPhoneNumberInfo.getPurePhoneNumber();
    }

    @Override
    public LoginVO login(LoginParam param) {
        WxMaJscode2SessionResult session = this.getSession(param.getCode());
        String openId = session.getOpenid();

        AppletUserEntity appletUser = appletUserMapper.selectByOpenId(openId);
        if (appletUser == null) {
            appletUser = new AppletUserEntity();
            appletUser.setOpenId(openId);
            appletUser.setUnionId(session.getUnionid());
            appletUser.setCreateTime(LocalDateTime.now());
            appletUser.setLastLoginTime(LocalDateTime.now());
            appletUser.setLastLoginIp(RequestUtils.getIpAddress());
            appletUser.setReferrer(param.getReferrer());
            if (StringUtils.isNotEmpty(param.getCode2())) {
                appletUser.setMobile(this.getPhoneNumber(param.getCode2()));
            }
            appletUserMapper.insert(appletUser);
        } else {
            appletUser.setLastLoginIp(RequestUtils.getIpAddress());
            appletUser.setLastLoginTime(LocalDateTime.now());
            appletUserMapper.updateById(appletUser);
        }

        String token = jwtUtils.generateToken(LoginUser.builder()
                .userId(appletUser.getId())
                .openId(appletUser.getOpenId())
                .build());
        return LoginVO.builder().token(token).build();
    }

    @Override
    public UserInfoVO getUserInfo() {
        AppletUserEntity appletUser = appletUserMapper.selectById(LoginUtils.getLoginId());
        if (appletUser == null) {
            throw new CustomException(ErrorCode.E401);
        }
        return UserInfoVO.builder()
                .avatar(appletUser.getHeadImgUrl())
                .mobile(DesensitizedUtil.mobilePhone(appletUser.getMobile()))
                .nickname(appletUser.getNickname())
                .build();
    }
}
