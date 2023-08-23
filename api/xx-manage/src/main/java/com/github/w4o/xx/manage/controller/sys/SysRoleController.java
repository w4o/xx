package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.role.RoleDTO;
import com.github.w4o.xx.manage.param.sys.role.AddRoleMenuParam;
import com.github.w4o.xx.manage.param.sys.role.AddRoleParam;
import com.github.w4o.xx.manage.param.sys.role.ModifyRoleParam;
import com.github.w4o.xx.manage.param.sys.role.RolePageParam;
import com.github.w4o.xx.manage.service.SysRoleService;
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
 * 系统角色控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "05. 角色管理")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<RoleDTO>> findPage(@ParameterObject @ModelAttribute RolePageParam param) {
        return CommonResult.success(sysRoleService.getPageList(param));
    }

    @Operation(summary = "添加角色")
    @SysLog("添加角色")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddRoleParam param) {
        sysRoleService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改角色")
    @Parameter(name = "id", required = true, description = "角色id")
    @SysLog("修改角色")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid ModifyRoleParam param) {
        sysRoleService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除角色")
    @Parameter(name = "id", required = true, description = "角色id")
    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysRoleService.delete(id);
        return CommonResult.success();
    }


    @Operation(summary = "查询全部角色", description = "用于下拉框数据")
    @GetMapping("/option")
    public CommonResult<?> findAll() {
        return CommonResult.success(sysRoleService.getAll());
    }

    @Operation(summary = "保存角色菜单")
    @SysLog("保存角色菜单")
    @PostMapping("/menu")
    public CommonResult<?> saveRoleMenu(@RequestBody @Valid AddRoleMenuParam param) {
        sysRoleService.addRoleMenu(param);
        return CommonResult.success();
    }

}
