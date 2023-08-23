package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.applet.nav.NavPageParam;
import com.github.w4o.xx.manage.param.applet.nav.NavParam;
import com.github.w4o.xx.manage.service.AppletNavService;
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
 * 小程序导航管理控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/nav")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "42. 小程序导航管理")
public class AppletNavController {

    private final AppletNavService appletNavService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute NavPageParam param) {
        return CommonResult.success(appletNavService.getPageList(param));
    }

    @SysLog("添加小程序导航")
    @Operation(summary = "添加导航")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid NavParam param) {
        appletNavService.add(param);
        return CommonResult.success();
    }

    @SysLog("修改小程序导航")
    @Operation(summary = "修改导航")
    @PutMapping("/{navId}")
    public CommonResult<?> modify(@PathVariable("navId") @NotNull Long navId,
                                  @RequestBody @Valid NavParam param) {
        appletNavService.update(navId, param);
        return CommonResult.success();
    }

    @SysLog("删除小程序导航类")
    @Operation(summary = "删除导航类")
    @DeleteMapping("/{navId}")
    public CommonResult<?> delete(@PathVariable("navId") @NotNull Long navId) {
        appletNavService.delete(navId);
        return CommonResult.success();
    }
}
