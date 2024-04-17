package com.github.w4o.xx.applet.controller;

import com.github.w4o.xx.applet.domain.param.SendSmsCodeParam;
import com.github.w4o.xx.applet.domain.vo.AppletConfigVO;
import com.github.w4o.xx.applet.domain.vo.SendSmsCodeVO;
import com.github.w4o.xx.applet.service.CommonService;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.vo.UploadVO;
import com.github.w4o.xx.service.impl.AppletBannerService;
import com.github.w4o.xx.service.impl.AppletConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Frank
 */
@Tag(name = "03. 公共接口")
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class CommonController {

    private final CommonService commonService;
    private final AppletConfigService appletConfigService;
    private final AppletBannerService appletBannerService;


    @Operation(summary = "小程序配置信息")
    @GetMapping("/config")
    public CommonResult<AppletConfigVO> config() {
        return CommonResult.success(AppletConfigVO.builder()
                .basic(appletConfigService.getConfig())
                .banner(appletBannerService.getList())
                .build());
    }

    @Operation(summary = "发送短信验证码")
    @PostMapping("/sendSmsCode")
    @CheckToken
    public CommonResult<SendSmsCodeVO> sendSmsCode(@RequestBody @Valid SendSmsCodeParam param) {
        return CommonResult.success(commonService.sendSmsCode(param));
    }

    @Operation(summary = "上传图片")
    @PostMapping("/upload/image")
    @CheckToken
    public CommonResult<UploadVO> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        return CommonResult.success(commonService.uploadImage(file));
    }


}
