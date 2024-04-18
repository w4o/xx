package com.github.w4o.xx.manage.controller.sys;


import cn.hutool.core.lang.tree.Tree;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.SysMenuEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.menu.MenuParam;
import com.github.w4o.xx.service.impl.sys.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Tag(name = "10. 菜单管理")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Operation(summary = "添加菜单")
    @SysLog("添加菜单")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid MenuParam param) {
        // 检查名称是否重复
        if (sysMenuService.exists(SysMenuEntity.gw().eq(SysMenuEntity::getName, param.getName()))) {
            throw new CustomException(ErrorCode.E1007);
        }
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(param, sysMenuEntity);
        sysMenuService.save(sysMenuEntity);
        return CommonResult.success();
    }

    @Operation(summary = "修改菜单")
    @Parameter(name = "id", required = true, description = "菜单id")
    @SysLog("修改菜单")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") @NotNull Long id,
                                     @RequestBody @Valid MenuParam param) {
        SysMenuEntity queryEntity = sysMenuService.getById(id);
        AssertUtils.notNull(queryEntity, ErrorCode.E1001);

        // 检查名称是否重复
        boolean exists = sysMenuService.exists(SysMenuEntity.gw()
                .eq(SysMenuEntity::getName, param.getName())
                .ne(SysMenuEntity::getId, id));
        if (exists) {
            throw new CustomException(ErrorCode.E1007);
        }
        BeanUtils.copyProperties(param, queryEntity);
        sysMenuService.updateById(queryEntity);

        return CommonResult.success();
    }

    @Operation(summary = "删除菜单")
    @Parameter(name = "id", required = true, description = "菜单id")
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysMenuService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "查询菜单树")
    @GetMapping("/tree")
    public CommonResult<List<Tree<Long>>> findMenuTree() {
        return CommonResult.success(sysMenuService.findTableTree());
    }

    @Operation(summary = "菜单树选项", description = "用于树形下拉列表数据")
    @GetMapping("/treeOption")
    public CommonResult<List<Tree<Long>>> menuTreeOptions() {
        return CommonResult.success(sysMenuService.getMenuTreeOptions());
    }

}
