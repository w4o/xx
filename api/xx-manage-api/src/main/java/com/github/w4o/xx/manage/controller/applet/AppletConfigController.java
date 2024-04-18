package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.applet.config.AddConfigParam;
import com.github.w4o.xx.manage.domain.param.applet.config.ConfigPageParam;
import com.github.w4o.xx.manage.domain.param.applet.config.ModifyConfigParam;
import com.github.w4o.xx.service.dto.applet.config.ConfigDTO;
import com.github.w4o.xx.service.impl.applet.AppletConfigService;
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
public class AppletConfigController extends BaseController {

    private final AppletConfigService appletConfigService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<ConfigDTO>> findPage(@ParameterObject @ModelAttribute ConfigPageParam param) {
        AppletConfigEntity entity = new AppletConfigEntity();
        return CommonResult.page(appletConfigService.getPageList(getIPage(param), entity));
    }

    @SysLog("添加小程序配置")
    @Operation(summary = "添加小程序配置")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddConfigParam param) {

        boolean exists = appletConfigService.exists(AppletConfigEntity.gw()
                .eq(AppletConfigEntity::getConfigKey, param.getConfigKey()));
        if (exists) {
            throw new CustomException(ErrorCode.E1300);
        }
        AppletConfigEntity entity = new AppletConfigEntity();
        BeanUtils.copyProperties(param, entity);
        appletConfigService.save(entity);

        return CommonResult.success();
    }

    @SysLog("修改小程序配置")
    @Operation(summary = "修改小程序配置")
    @PutMapping("/{configId}")
    public CommonResult<Void> modify(@PathVariable("configId") @NotNull Long configId,
                                     @RequestBody @Valid ModifyConfigParam param) {
        AppletConfigEntity entity = appletConfigService.getById(configId);
        AssertUtils.notNull(entity, ErrorCode.E1301);
        BeanUtils.copyProperties(param, entity);
        appletConfigService.updateById(entity);
        return CommonResult.success();
    }

    @SysLog("删除小程序配置类")
    @Operation(summary = "删除小程序配置类")
    @DeleteMapping("/{configId}")
    public CommonResult<Void> delete(@PathVariable("configId") @NotNull Long configId) {
        appletConfigService.removeById(configId);
        return CommonResult.success();
    }
}
