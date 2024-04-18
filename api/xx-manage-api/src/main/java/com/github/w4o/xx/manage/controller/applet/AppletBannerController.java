package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.applet.banner.BannerPageParam;
import com.github.w4o.xx.manage.domain.param.applet.banner.BannerParam;
import com.github.w4o.xx.service.dto.applet.banner.BannerDTO;
import com.github.w4o.xx.service.impl.applet.AppletBannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class AppletBannerController extends BaseController {

    private final AppletBannerService appletBannerService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<BannerDTO>> findPage(@ParameterObject @ModelAttribute BannerPageParam param) {
        AppletBannerEntity entity = new AppletBannerEntity();
        BeanUtils.copyProperties(param, entity);
        return CommonResult.page(appletBannerService.getPageList(getIPage(param), entity));
    }

    @SysLog("添加轮播图")
    @Operation(summary = "添加轮播图")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid BannerParam param) {
        AppletBannerEntity entity = new AppletBannerEntity();
        BeanUtils.copyProperties(param, entity);
        appletBannerService.save(entity);
        return CommonResult.success();
    }

    @SysLog("修改轮播图")
    @Operation(summary = "修改轮播图")
    @PutMapping("/{bannerId}")
    public CommonResult<Void> modify(@PathVariable("bannerId") @NotNull Long bannerId,
                                     @RequestBody @Valid BannerParam param) {
        AppletBannerEntity entity = appletBannerService.getById(bannerId);
        AssertUtils.notNull(entity);
        BeanUtils.copyProperties(param, entity);
        appletBannerService.updateById(entity);
        return CommonResult.success();
    }

    @SysLog("删除轮播图类")
    @Operation(summary = "删除轮播图类")
    @DeleteMapping("/{bannerId}")
    public CommonResult<Void> delete(@PathVariable("bannerId") @NotNull Long bannerId) {
        appletBannerService.removeById(bannerId);
        return CommonResult.success();
    }

    @SysLog("显示轮播图")
    @Operation(summary = "显示轮播图")
    @PutMapping("/{bannerId}/visible")
    public CommonResult<Void> visible(@PathVariable("bannerId") @NotNull Long bannerId) {
        AppletBannerEntity entity = appletBannerService.getById(bannerId);
        AssertUtils.notNull(entity);
        entity.setVisible(true);
        appletBannerService.updateById(entity);
        return CommonResult.success();
    }

    @SysLog("隐藏轮播图")
    @Operation(summary = "隐藏轮播图")
    @PutMapping("/{bannerId}/invisible")
    public CommonResult<Void> invisible(@PathVariable("bannerId") @NotNull Long bannerId) {
        AppletBannerEntity entity = appletBannerService.getById(bannerId);
        AssertUtils.notNull(entity);
        entity.setVisible(false);
        appletBannerService.updateById(entity);
        return CommonResult.success();
    }
}
