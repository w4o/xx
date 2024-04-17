package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.param.sys.user.AddUserParam;
import com.github.w4o.xx.manage.param.sys.user.ModifyUserParam;
import com.github.w4o.xx.manage.param.sys.user.UserPageParam;
import com.github.w4o.xx.manage.service.SysUserService;
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
 * 系统用户控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "06. 系统用户管理")
public class SysUserController {

    private final SysUserService sysUserService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute UserPageParam param) {
        return CommonResult.success(sysUserService.getPageList(param));
    }

    @Operation(summary = "添加系统用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("添加系统用户")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddUserParam param) {
        sysUserService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改系统用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("修改系统用户")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid ModifyUserParam param) {
        sysUserService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("删除系统用户")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysUserService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "用户详情")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}")
    public CommonResult<?> info(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "禁用用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("禁用系统用户")
    @PutMapping("/{id}/disable")
    public CommonResult<?> disable(@PathVariable("id") @NotNull Long id) {
        sysUserService.disable(id);
        return CommonResult.success();
    }

    @Operation(summary = "启用用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("启用系统用户")
    @PutMapping("/{id}/enable")
    public CommonResult<?> enable(@PathVariable("id") @NotNull Long id) {
        sysUserService.enable(id);
        return CommonResult.success();
    }

    @Operation(summary = "查询用户权限")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}/permissions")
    public CommonResult<?> findPermissions(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "查询用户角色")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}/roles")
    public CommonResult<?> findUserRoles(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "重置密码")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("重置系统用户密码")
    @PutMapping("/{id}/resetPassword")
    public CommonResult<?> resetPassword(@PathVariable("id") @NotNull Long id) {
        if (id == 1L) {
            throw new CustomException(ErrorCode.E1011);
        }
        sysUserService.resetPassword(id);
        return CommonResult.success();
    }

}
