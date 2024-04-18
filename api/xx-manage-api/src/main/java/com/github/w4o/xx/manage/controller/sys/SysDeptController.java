package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.lang.tree.Tree;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.SysDeptEntity;
import com.github.w4o.xx.manage.domain.param.sys.dept.AddDeptParam;
import com.github.w4o.xx.manage.domain.param.sys.dept.ModifyDeptParam;
import com.github.w4o.xx.service.impl.sys.SysDeptService;
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
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "04. 部门管理")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @PostMapping
    @Operation(summary = "添加部门")
    public CommonResult<Void> add(@RequestBody @Valid AddDeptParam param) {
        SysDeptEntity entity = new SysDeptEntity();
        BeanUtils.copyProperties(param, entity);
        sysDeptService.add(entity);
        return CommonResult.success();
    }

    @PutMapping("/{id}")
    @Parameter(name = "id", required = true, description = "部门id")
    @Operation(summary = "修改部门")
    public CommonResult<Void> modify(@PathVariable("id") @NotNull Long id,
                                     @RequestBody @Valid ModifyDeptParam param) {
        SysDeptEntity entity = new SysDeptEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(id);
        sysDeptService.update(entity);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "id", required = true, description = "部门id")
    @Operation(summary = "删除部门")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysDeptService.delete(id);
        return CommonResult.success();
    }

    @GetMapping("/tree")
    @Operation(summary = "查询机部门")
    public CommonResult<List<Tree<Long>>> findTree() {
        return CommonResult.success(sysDeptService.findTree());
    }
}
