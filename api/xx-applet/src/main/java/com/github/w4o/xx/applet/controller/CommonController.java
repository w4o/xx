package com.github.w4o.xx.applet.controller;

import com.github.w4o.xx.applet.param.SendSmsCodeParam;
import com.github.w4o.xx.applet.service.AppletConfigService;
import com.github.w4o.xx.applet.service.CommonService;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.manage.SysDictManage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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


    @Operation(summary = "小程序配置信息")
    @GetMapping("/config")
    public CommonResult<?> config() {
        Map<String, Object> result = new HashMap<>();
        result.put("basic", appletConfigService.getConfig());
        return CommonResult.success(result);
    }

    @Operation(summary = "发送短信验证码")
    @PostMapping("/sendSmsCode")
    @CheckToken
    public CommonResult<?> sendSmsCode(@RequestBody @Valid SendSmsCodeParam param) {
        return CommonResult.success(commonService.sendSmsCode(param));
    }

    @Operation(summary = "上传图片")
    @PostMapping("/upload/image")
    @CheckToken
    public CommonResult<?> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        return CommonResult.success(commonService.uploadImage(file));
    }


}
