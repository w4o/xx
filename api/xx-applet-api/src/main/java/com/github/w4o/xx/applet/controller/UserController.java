package com.github.w4o.xx.applet.controller;

import com.github.w4o.xx.applet.common.util.LoginUtils;
import com.github.w4o.xx.applet.domain.param.AppletBindPhoneParam;
import com.github.w4o.xx.applet.domain.param.UpdateAvatarParam;
import com.github.w4o.xx.applet.domain.param.UpdateNicknameParam;
import com.github.w4o.xx.applet.service.CommonService;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.service.impl.applet.AppletUserService;
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
    public CommonResult<Void> updateNickname(@RequestBody @Valid UpdateNicknameParam param) {
        AppletUserEntity appletUser = new AppletUserEntity();
        appletUser.setId(LoginUtils.getLoginId());
        appletUser.setNickname(param.getNickname());
        appletUserService.updateById(appletUser);
        return CommonResult.success();
    }

    @Operation(summary = "更新头像")
    @PostMapping("/avatar")
    @CheckToken
    public CommonResult<Void> updateAvatar(@RequestBody @Valid UpdateAvatarParam param) {
        AppletUserEntity appletUser = new AppletUserEntity();
        appletUser.setId(LoginUtils.getLoginId());
        appletUser.setHeadImgUrl(param.getAvatar());
        appletUserService.updateById(appletUser);
        return CommonResult.success();
    }

    @Operation(summary = "绑定手机号")
    @PostMapping("/bindPhone")
    @CheckToken
    public CommonResult<Void> bindPhone(@RequestBody @Valid AppletBindPhoneParam param) {
        // 验证手机验证吗
        commonService.validateSmsCode(param.getPhone(), param.getSmsCode());
        // 更新手机号
        AppletUserEntity appletUser = new AppletUserEntity();
        appletUser.setId(LoginUtils.getLoginId());
        appletUser.setMobile(param.getPhone());
        appletUserService.updateById(appletUser);
        return CommonResult.success();
    }

}
