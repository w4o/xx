package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.applet.nav.AddNavParam;
import com.github.w4o.xx.manage.param.applet.nav.ModifyNavParam;
import com.github.w4o.xx.manage.param.applet.nav.NavPageParam;
import com.github.w4o.xx.manage.service.AppletNavService;
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

    @Operation(summary = "添加导航")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddNavParam param) {
        appletNavService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改导航")
    @PutMapping("/{navId}")
    public CommonResult<?> modify(@PathVariable("navId") @NotNull Long navId,
                                  @RequestBody @Valid ModifyNavParam param) {
        appletNavService.update(navId, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除导航类")
    @DeleteMapping("/{navId}")
    public CommonResult<?> delete(@PathVariable("navId") @NotNull Long navId) {
        appletNavService.delete(navId);
        return CommonResult.success();
    }
}
