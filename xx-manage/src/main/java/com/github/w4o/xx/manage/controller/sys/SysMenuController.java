package com.github.w4o.xx.manage.controller.sys;


import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.menu.MenuParam;
import com.github.w4o.xx.manage.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 系统菜单控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "菜单管理")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Operation(summary = "添加菜单")
    @SysLog("添加菜单")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid MenuParam param) {
        sysMenuService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改菜单")
    @SysLog("修改菜单")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid MenuParam param) {
        sysMenuService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除菜单")
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysMenuService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "查询菜单树")
    @GetMapping("/tree")
    public CommonResult<?> findMenuTree() {
        return CommonResult.success(sysMenuService.findMenuTree());
    }

    @Operation(summary = "菜单树选项", description = "用于树形下拉列表数据")
    @GetMapping("/treeOption")
    public CommonResult<?> menuTreeOptions() {
        return CommonResult.success(sysMenuService.getMenuTreeOptions());
    }

}
