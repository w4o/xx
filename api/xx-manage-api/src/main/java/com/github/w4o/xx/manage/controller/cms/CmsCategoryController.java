package com.github.w4o.xx.manage.controller.cms;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.cms.category.CategoryPageParam;
import com.github.w4o.xx.manage.param.cms.category.CategoryParam;
import com.github.w4o.xx.manage.service.CmsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
 * 文章分类控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cms/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "31. 分类管理")
public class CmsCategoryController {

    private final CmsCategoryService cmsCategoryService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute CategoryPageParam param) {
        return CommonResult.success(cmsCategoryService.getPageList(param));
    }

    @SysLog("添加分类")
    @Operation(summary = "添加分类")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid CategoryParam param) {
        return CommonResult.success(cmsCategoryService.add(param));
    }

    @SysLog("修改分类")
    @Operation(summary = "修改分类")
    @PutMapping("/{categoryId}")
    public CommonResult<?> modify(@PathVariable("categoryId") @NotNull Long categoryId,
                                  @RequestBody @Valid CategoryParam param) {
        cmsCategoryService.update(categoryId, param);
        return CommonResult.success();
    }

    @SysLog("删除分类")
    @Operation(summary = "删除分类")
    @DeleteMapping("/{categoryId}")
    public CommonResult<?> delete(@PathVariable("categoryId") @NotNull Long categoryId) {
        cmsCategoryService.delete(categoryId);
        return CommonResult.success();
    }

    @Operation(summary = "获取分类树")
    @GetMapping("/tree")
    public CommonResult<?> getTree() {
        return CommonResult.success(cmsCategoryService.getTree());
    }

}
