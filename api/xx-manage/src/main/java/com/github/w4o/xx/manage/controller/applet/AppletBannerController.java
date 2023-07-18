package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.applet.banner.AddBannerParam;
import com.github.w4o.xx.manage.param.applet.banner.BannerPageParam;
import com.github.w4o.xx.manage.param.applet.banner.ModifyBannerParam;
import com.github.w4o.xx.manage.service.AppletBannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 小程序轮播图管理控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/banner")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "40. 小程序轮播图管理")
public class AppletBannerController {

    private final AppletBannerService appletBannerService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute BannerPageParam param) {
        return CommonResult.success(appletBannerService.getPageList(param));
    }

    @Operation(summary = "添加轮播图")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddBannerParam param) {
        appletBannerService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改轮播图")
    @PutMapping("/{bannerId}")
    public CommonResult<?> modify(@PathVariable("bannerId") @NotNull Long bannerId,
                                  @RequestBody @Valid ModifyBannerParam param) {
        appletBannerService.update(bannerId, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除轮播图类")
    @DeleteMapping("/{bannerId}")
    public CommonResult<?> delete(@PathVariable("bannerId") @NotNull Long bannerId) {
        appletBannerService.delete(bannerId);
        return CommonResult.success();
    }
}
