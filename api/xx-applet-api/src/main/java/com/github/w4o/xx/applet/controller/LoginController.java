package com.github.w4o.xx.applet.controller;

import com.github.w4o.xx.applet.domain.param.LoginParam;
import com.github.w4o.xx.applet.service.LoginService;
import com.github.w4o.xx.applet.vo.LoginVO;
import com.github.w4o.xx.applet.vo.UserInfoVO;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.base.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frank
 */
@Tag(name = "02. 小程序用户登录相关")
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "登陆")
    @PostMapping("/login")
    public CommonResult<LoginVO> login(@RequestBody LoginParam param) {
        return CommonResult.success(loginService.login(param));
    }

    @Operation(summary = "用户信息")
    @GetMapping("/userInfo")
    @CheckToken
    public CommonResult<UserInfoVO> userInfo() {
        return CommonResult.success(loginService.getUserInfo());
    }
}
