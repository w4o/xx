package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.param.sys.user.AddUserParam;
import com.github.w4o.xx.manage.param.sys.user.ModifyUserParam;
import com.github.w4o.xx.manage.param.sys.user.UserPageParam;
import com.github.w4o.xx.manage.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
public class SysUserController {

    private final SysUserService sysUserService;

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(UserPageParam param) {
        return CommonResult.success(sysUserService.getPageList(param));
    }

    /**
     * 添加用户
     */
    @SysLog("添加系统用户")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddUserParam param) {
        sysUserService.add(param);
        return CommonResult.success();
    }

    /**
     * 修改用户信息
     */
    @SysLog("修改系统用户")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid ModifyUserParam param) {
        sysUserService.update(id, param);
        return CommonResult.success();
    }

    /**
     * 删除用户
     */
    @SysLog("删除系统用户")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysUserService.delete(id);
        return CommonResult.success();
    }


    /**
     * 用户详情
     */
    @GetMapping("/{id}")
    public CommonResult<?> info(@PathVariable("id") @NotNull Long id) {
        return CommonResult.success();
    }

    /**
     * 禁用用户
     */
    @SysLog("禁用系统用户")
    @PutMapping("/{id}/disable")
    public CommonResult<?> disable(@PathVariable("id") @NotNull Long id) {
        sysUserService.disable(id);
        return CommonResult.success();
    }

    /**
     * 启用用户
     */
    @SysLog("启用系统用户")
    @PutMapping("/{id}/enable")
    public CommonResult<?> enable(@RequestParam("id") @NotNull Long id) {
        sysUserService.enable(id);
        return CommonResult.success();
    }

    /**
     * 查询用户权限
     */
    @GetMapping("/findPermissions")
    public CommonResult<?> findPermissions() {
        return CommonResult.success();
    }

    /**
     * 查询用户角色
     */
    @GetMapping("/findUserRoles")
    public CommonResult<?> findUserRoles() {
        return CommonResult.success();
    }

    /**
     * 重置密码
     */
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
