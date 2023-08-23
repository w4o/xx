package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.config.SysConfigDTO;
import com.github.w4o.xx.manage.param.sys.config.AddConfigParam;
import com.github.w4o.xx.manage.param.sys.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.sys.config.ModifyConfigParam;
import com.github.w4o.xx.manage.service.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 * 系统配置控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/config")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "09. 配置管理")
public class SysConfigController {

    private final SysConfigService sysConfigService;

    @Operation(summary = "添加配置")
    @SysLog("添加配置")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddConfigParam param) {
        sysConfigService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改配置")
    @Parameter(name = "id", required = true, description = "配置id")
    @SysLog("修改配置")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyConfigParam param) {
        sysConfigService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除配置")
    @Parameter(name = "id", required = true, description = "配置id")
    @SysLog("删除配置")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysConfigService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<SysConfigDTO>> findPage(@ParameterObject @ModelAttribute ConfigPageParam param) {
        return CommonResult.success(sysConfigService.getPageList(param));
    }
}
