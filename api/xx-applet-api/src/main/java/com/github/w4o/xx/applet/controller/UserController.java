package com.github.w4o.xx.applet.controller;

import com.github.w4o.xx.applet.param.AppletBindPhoneParam;
import com.github.w4o.xx.applet.param.UpdateAvatarParam;
import com.github.w4o.xx.applet.param.UpdateNicknameParam;
import com.github.w4o.xx.applet.service.AppletUserService;
import com.github.w4o.xx.applet.service.CommonService;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.base.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frank
 */
@Tag(name = "03. 小程序用户")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class UserController {

    private final AppletUserService appletUserService;
    private final CommonService commonService;

    @Operation(summary = "更新昵称")
    @PutMapping("/nickname")
    @CheckToken
    public CommonResult<?> updateNickname(@RequestBody @Valid UpdateNicknameParam param) {
        appletUserService.updateNickname(param);
        return CommonResult.success();
    }

    @Operation(summary = "更新头像")
    @PostMapping("/avatar")
    @CheckToken
    public CommonResult<?> updateAvatar(@RequestBody @Valid UpdateAvatarParam param) {
        appletUserService.updateAvatar(param);
        return CommonResult.success();
    }

    @Operation(summary = "绑定手机号")
    @PostMapping("/bindPhone")
    @CheckToken
    public CommonResult<?> bindPhone(@RequestBody @Valid AppletBindPhoneParam param) {
        commonService.validateSmsCode(param.getPhone(), param.getSmsCode());
        appletUserService.bindPhone(param);
        return CommonResult.success();
    }

}
