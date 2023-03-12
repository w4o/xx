package com.github.w4o.xx.manage.controller;


import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.util.BusinessUtils;
import com.github.w4o.xx.manage.common.UserInfo;
import com.github.w4o.xx.manage.common.config.AppConfig;
import com.github.w4o.xx.manage.common.util.JwtUtils;
import com.github.w4o.xx.manage.param.ChangePasswordParam;
import com.github.w4o.xx.manage.param.LoginParam;
import com.github.w4o.xx.manage.service.SysLoginLogService;
import com.github.w4o.xx.manage.service.SysMenuService;
import com.github.w4o.xx.manage.service.SysUserService;
import com.github.w4o.xx.manage.vo.LoginVO;
import com.github.w4o.xx.manage.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 登陆控制器
 *
 * @author Frank
 */
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class LoginController {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final SysUserService sysUserService;
    private final SysMenuService sysMenuService;
    private final SysLoginLogService sysLoginLogService;
    private final AppConfig appConfig;
    private final BusinessUtils businessUtils;

    /**
     * 登陆
     */
    @PostMapping("/login")
    public CommonResult<LoginVO> login(@RequestBody LoginParam loginParam) {
        // 密码解密
        String password = businessUtils.aesDecrypt(appConfig.getAesKey(), loginParam.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        var token = jwtUtils.generateToken(userDetails);
        // 登录日志
        try {
            sysLoginLogService.add(loginParam.getUsername());
        } catch (Exception e) {
            log.error("登录日志插入失败", e);
        }
        return CommonResult.success(LoginVO.builder()
                .username(loginParam.getUsername())
                .accessToken(token)
                .refreshToken("")
                .roles(List.of("*"))
                .expires(LocalDateTime.MAX)
                .build());
    }

    /**
     * 用户信息
     */
    @GetMapping({"/userInfo", "/admin_info"})
    public CommonResult<UserInfoVO> userInfo(Principal principal) {
        // TODO 测试用户信息
        UserInfoVO userInfoVO = UserInfoVO.builder()
                .avatar("https://dn-qiniu-avatar.qbox.me/avatar/")
                .username(principal.getName())
                .permissions(new String[]{"*"})
                .roles(new String[]{"*"})
                .build();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("{}", userInfo.getUserId());

        return CommonResult.success(userInfoVO);
    }

    /**
     * 修改密码
     */
    @SysLog("用户修改密码")
    @PutMapping("/changePassword")
    public CommonResult<?> changePassword(@RequestBody @Valid ChangePasswordParam param) {
        sysUserService.changePassword(param);
        return CommonResult.success();
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public CommonResult<?> logout() {
        return CommonResult.success();
    }

    /**
     * 前端路由
     */
    @GetMapping("/menus")
    public CommonResult<?> getRouter() {
        return CommonResult.success(sysMenuService.findNavTree());
    }

}
