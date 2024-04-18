package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.AppletNavEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.applet.nav.NavPageParam;
import com.github.w4o.xx.manage.domain.param.applet.nav.NavParam;
import com.github.w4o.xx.service.dto.applet.nav.NavDTO;
import com.github.w4o.xx.service.impl.applet.AppletNavService;
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
public class AppletNavController extends BaseController {

    private final AppletNavService appletNavService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<NavDTO>> findPage(@ParameterObject @ModelAttribute NavPageParam param) {
        AppletNavEntity entity = new AppletNavEntity();
        return CommonResult.page(appletNavService.getPageList(getIPage(param), entity));
    }

    @SysLog("添加小程序导航")
    @Operation(summary = "添加导航")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid NavParam param) {
        AppletNavEntity entity = new AppletNavEntity();
        BeanUtils.copyProperties(param, entity);
        appletNavService.save(entity);
        return CommonResult.success();
    }

    @SysLog("修改小程序导航")
    @Operation(summary = "修改导航")
    @PutMapping("/{navId}")
    public CommonResult<Void> modify(@PathVariable("navId") @NotNull Long navId,
                                     @RequestBody @Valid NavParam param) {
        AppletNavEntity entity = appletNavService.getById(navId);
        AssertUtils.notNull(entity, ErrorCode.E1302);
        BeanUtils.copyProperties(param, entity);
        appletNavService.updateById(entity);
        return CommonResult.success();
    }

    @SysLog("删除小程序导航类")
    @Operation(summary = "删除导航类")
    @DeleteMapping("/{navId}")
    public CommonResult<Void> delete(@PathVariable("navId") @NotNull Long navId) {
        appletNavService.removeById(navId);
        return CommonResult.success();
    }
}
