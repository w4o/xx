package com.github.w4o.xx.applet.service;

import com.github.w4o.xx.applet.param.LoginParam;
import com.github.w4o.xx.applet.vo.LoginVO;
import com.github.w4o.xx.applet.vo.UserInfoVO;

/**
 * @author Frank
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param param 登录参数
     * @return 登录结果
     */
    LoginVO login(LoginParam param);

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    UserInfoVO getUserInfo();

}
