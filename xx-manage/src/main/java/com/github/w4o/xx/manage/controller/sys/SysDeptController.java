package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.dept.AddDeptParam;
import com.github.w4o.xx.manage.param.sys.dept.ModifyDeptParam;
import com.github.w4o.xx.manage.service.SysDeptService;
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
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "部门管理")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @PostMapping
    @Operation(summary = "添加部门")
    public CommonResult<?> add(@RequestBody @Valid AddDeptParam addDeptParam) {
        sysDeptService.add(addDeptParam);
        return CommonResult.success();
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改部门")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid ModifyDeptParam param) {
        sysDeptService.update(id, param);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysDeptService.delete(id);
        return CommonResult.success();
    }

    @GetMapping("/tree")
    @Operation(summary = "查询机部门")
    public CommonResult<?> findTree() {
        return CommonResult.success(sysDeptService.findTree());
    }
}
