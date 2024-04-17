package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.applet.config.AddConfigParam;
import com.github.w4o.xx.manage.param.applet.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.applet.config.ModifyConfigParam;
import com.github.w4o.xx.manage.service.AppletConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序配置管理控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/config")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "43. 小程序配置管理")
public class AppletConfigController {

    private final AppletConfigService appletConfigService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute ConfigPageParam param) {
        return CommonResult.success(appletConfigService.getPageList(param));
    }

    @SysLog("添加小程序配置")
    @Operation(summary = "添加小程序配置")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddConfigParam param) {
        appletConfigService.add(param);
        return CommonResult.success();
    }

    @SysLog("修改小程序配置")
    @Operation(summary = "修改小程序配置")
    @PutMapping("/{configId}")
    public CommonResult<?> modify(@PathVariable("configId") @NotNull Long configId,
                                  @RequestBody @Valid ModifyConfigParam param) {
        appletConfigService.update(configId, param);
        return CommonResult.success();
    }

    @SysLog("删除小程序配置类")
    @Operation(summary = "删除小程序配置类")
    @DeleteMapping("/{configId}")
    public CommonResult<?> delete(@PathVariable("configId") @NotNull Long configId) {
        appletConfigService.delete(configId);
        return CommonResult.success();
    }
}
