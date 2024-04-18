package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.core.util.BusinessUtils;
import com.github.w4o.xx.manage.domain.param.sys.user.AddUserParam;
import com.github.w4o.xx.manage.domain.param.sys.user.ModifyUserParam;
import com.github.w4o.xx.manage.domain.param.sys.user.UserPageParam;
import com.github.w4o.xx.service.dto.sys.user.UserDTO;
import com.github.w4o.xx.service.impl.sys.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.w4o.xx.core.constant.Constant.DEFAULT_PASSWORD;

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
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<UserDTO>> findPage(@ParameterObject @ModelAttribute UserPageParam param) {
        SysUserEntity entity = new SysUserEntity();
        BeanUtils.copyProperties(param, entity);
        return CommonResult.page(sysUserService.getPageList(getIPage(param), entity));
    }

    @Operation(summary = "添加系统用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("添加系统用户")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddUserParam param) {
        SysUserEntity entity = new SysUserEntity();
        BeanUtils.copyProperties(param, entity);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String password = BusinessUtils.decrypt(param.getPassword());
        entity.setPassword(encoder.encode(password));

        sysUserService.add(entity, param.getRoles());
        return CommonResult.success();
    }

    @Operation(summary = "修改系统用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("修改系统用户")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") @NotNull Long id,
                                     @RequestBody @Valid ModifyUserParam param) {
        SysUserEntity entity = new SysUserEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(id);
        sysUserService.update(entity, param.getRoles());
        return CommonResult.success();
    }

    @Operation(summary = "删除用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("删除系统用户")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysUserService.removeById(id);
        return CommonResult.success();
    }

    @Operation(summary = "用户详情")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}")
    public CommonResult<Void> info(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "禁用用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("禁用系统用户")
    @PutMapping("/{id}/disable")
    public CommonResult<Void> disable(@PathVariable("id") @NotNull Long id) {
        SysUserEntity queryEntity = sysUserService.getById(id);
        AssertUtils.notNull(queryEntity);
        queryEntity.setStatus(0);
        sysUserService.updateById(queryEntity);
        return CommonResult.success();
    }

    @Operation(summary = "启用用户")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("启用系统用户")
    @PutMapping("/{id}/enable")
    public CommonResult<Void> enable(@PathVariable("id") @NotNull Long id) {
        SysUserEntity queryEntity = sysUserService.getById(id);
        AssertUtils.notNull(queryEntity);
        queryEntity.setStatus(1);
        sysUserService.updateById(queryEntity);
        return CommonResult.success();
    }

    @Operation(summary = "查询用户权限")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}/permissions")
    public CommonResult<Void> findPermissions(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "查询用户角色")
    @Parameter(name = "id", required = true, description = "用户id")
    @GetMapping("/{id}/roles")
    public CommonResult<Void> findUserRoles(@PathVariable("id") @NotNull Long id) {
        // TODO
        log.debug("id: {}", id);
        return CommonResult.success();
    }

    @Operation(summary = "重置密码")
    @Parameter(name = "id", required = true, description = "用户id")
    @SysLog("重置系统用户密码")
    @PutMapping("/{id}/resetPassword")
    public CommonResult<Void> resetPassword(@PathVariable("id") @NotNull Long id) {
        if (id == 1L) {
            throw new CustomException(ErrorCode.E1011);
        }
        var queryEntity = sysUserService.getById(id);
        AssertUtils.notNull(queryEntity);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        queryEntity.setPassword(encoder.encode(DEFAULT_PASSWORD));
        sysUserService.updateById(queryEntity);
        return CommonResult.success();
    }

}
