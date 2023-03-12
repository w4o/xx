package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.config.AddConfigParam;
import com.github.w4o.xx.manage.param.sys.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.sys.config.ModifyConfigParam;
import com.github.w4o.xx.manage.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
public class SysConfigController {

    private final SysConfigService sysConfigService;

    /**
     * 添加配置
     */
    @SysLog("添加配置")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddConfigParam param) {
        sysConfigService.add(param);
        return CommonResult.success();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyConfigParam param) {
        sysConfigService.update(id, param);
        return CommonResult.success();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysConfigService.delete(id);
        return CommonResult.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(ConfigPageParam param) {
        return CommonResult.success(sysConfigService.getPageList(param));
    }
}
