package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.role.AddRoleMenuParam;
import com.github.w4o.xx.manage.param.sys.role.AddRoleParam;
import com.github.w4o.xx.manage.param.sys.role.ModifyRoleParam;
import com.github.w4o.xx.manage.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class SysRoleController {

    private final SysRoleService sysRoleService;

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(@RequestParam("pageNo") @NotNull @Min(1) Long pageNo,
                                    @RequestParam("pageSize") @NotNull @Max(100) Long pageSize) {
        return CommonResult.success(sysRoleService.getPageList(pageNo, pageSize));
    }

    /**
     * 添加角色
     */
    @SysLog("添加角色")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddRoleParam param) {
        sysRoleService.add(param);
        return CommonResult.success();
    }

    /**
     * 修改角色信息
     */
    @SysLog("修改角色")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid ModifyRoleParam param) {
        sysRoleService.update(id, param);
        return CommonResult.success();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysRoleService.delete(id);
        return CommonResult.success();
    }


    /**
     * 查询全部角色
     */
    @GetMapping("/option")
    public CommonResult<?> findAll() {
        return CommonResult.success(sysRoleService.getAll());
    }

    /**
     * 保存角色菜单
     */
    @SysLog("保存角色菜单")
    @PostMapping("/menu")
    public CommonResult<?> saveRoleMenu(@RequestBody @Valid AddRoleMenuParam param) {
        sysRoleService.addRoleMenu(param);
        return CommonResult.success();
    }

}
