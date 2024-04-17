package com.github.w4o.xx.manage.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.BusinessUtils;
import com.github.w4o.xx.manage.common.UserInfo;
import com.github.w4o.xx.manage.common.config.AppConfig;
import com.github.w4o.xx.manage.common.util.JwtUtils;
import com.github.w4o.xx.manage.param.ChangePasswordParam;
import com.github.w4o.xx.manage.param.LoginParam;
import com.github.w4o.xx.manage.service.SysLoginLogService;
import com.github.w4o.xx.manage.service.SysMenuService;
import com.github.w4o.xx.manage.service.SysUserService;
import com.github.w4o.xx.manage.vo.CaptchaVO;
import com.github.w4o.xx.manage.vo.LoginVO;
import com.github.w4o.xx.manage.vo.UserInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录控制器
 *
 * @author Frank
 */

@Tag(name = "02. 系统用户登录相关")
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
    private final RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public CommonResult<LoginVO> login(@RequestBody @Valid LoginParam loginParam) {
        if (!BusinessUtils.isDebug()) {
            String cacheKey = "captcha:" + loginParam.getCaptchaKey();
            String code = (String) redisTemplate.opsForValue().get(cacheKey);
            redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(cacheKey)));
            if (StringUtils.isEmpty(code) || !StringUtils.equalsIgnoreCase(loginParam.getVerificationCode(), code)) {
                return CommonResult.error(ErrorCode.E1003);
            }
        }
        // 密码解密
        String password = BusinessUtils.decrypt(loginParam.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), password);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException ignore) {
            throw new CustomException(ErrorCode.E1002);
        }
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
                .accessToken(token)
                .refreshToken("")
                .expires(LocalDateTime.now().plusSeconds(appConfig.getJwt().getExpire()))
                .build());
    }

    @Operation(summary = "获取登录用户信息")
    @GetMapping("/userInfo")
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

    @SysLog("用户修改密码")
    @Operation(summary = "修改密码")
    @PutMapping("/changePassword")
    public CommonResult<?> changePassword(@RequestBody @Valid ChangePasswordParam param) {
        sysUserService.changePassword(param);
        return CommonResult.success();
    }

    @Operation(summary = "登出")
    @GetMapping("/logout")
    public CommonResult<?> logout() {
        return CommonResult.success();
    }

    @Operation(summary = "前端路由（菜单）")
    @GetMapping("/menus")
    public CommonResult<?> getRouter() {
        return CommonResult.success(sysMenuService.findNavTree());
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public CommonResult<CaptchaVO> captcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 100);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("captcha:" + uuid, lineCaptcha.getCode(), 30, TimeUnit.MINUTES);
        return CommonResult.success(CaptchaVO.builder().captchaKey(uuid).captchaImg(lineCaptcha.getImageBase64()).build());
    }

}
