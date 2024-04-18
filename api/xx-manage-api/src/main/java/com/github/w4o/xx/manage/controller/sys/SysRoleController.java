package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysRoleEntity;
import com.github.w4o.xx.manage.domain.param.sys.role.AddRoleMenuParam;
import com.github.w4o.xx.manage.domain.param.sys.role.AddRoleParam;
import com.github.w4o.xx.manage.domain.param.sys.role.ModifyRoleParam;
import com.github.w4o.xx.manage.domain.param.sys.role.RolePageParam;
import com.github.w4o.xx.service.dto.sys.role.RoleDTO;
import com.github.w4o.xx.service.impl.sys.SysRoleService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<RoleDTO>> findPage(@ParameterObject @ModelAttribute RolePageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(sysRoleService.getPageList(getIPage(param), condition));
    }

    @Operation(summary = "添加角色")
    @SysLog("添加角色")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddRoleParam param) {
        SysRoleEntity entity = new SysRoleEntity();
        BeanUtils.copyProperties(param, entity);
        sysRoleService.add(entity, param.getMenus());
        return CommonResult.success();
    }

    @Operation(summary = "修改角色")
    @Parameter(name = "id", required = true, description = "角色id")
    @SysLog("修改角色")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") @NotNull Long id,
                                     @RequestBody @Valid ModifyRoleParam param) {
        SysRoleEntity entity = new SysRoleEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(id);
        sysRoleService.update(entity, param.getMenus());
        return CommonResult.success();
    }

    @Operation(summary = "删除角色")
    @Parameter(name = "id", required = true, description = "角色id")
    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysRoleService.delete(id);
        return CommonResult.success();
    }


    @Operation(summary = "查询全部角色", description = "用于下拉框数据")
    @GetMapping("/option")
    public CommonResult<List<Map<String, Object>>> findAll() {
        return CommonResult.success(sysRoleService.getAll());
    }

    @Operation(summary = "保存角色菜单")
    @SysLog("保存角色菜单")
    @PostMapping("/menu")
    public CommonResult<Void> saveRoleMenu(@RequestBody @Valid AddRoleMenuParam param) {
        sysRoleService.addRoleMenu(param.getRoleId(), param.getMenuIds());
        return CommonResult.success();
    }

}
