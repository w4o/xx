package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.media.category.MediaCategoryParam;
import com.github.w4o.xx.manage.service.SysMediaCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/sys/media-category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "14. 媒体分类管理")
public class SysMediaCategoryController {

    private final SysMediaCategoryService sysMediaCategoryService;

    @Operation(summary = "添加媒体分类")
    @SysLog("添加媒体分类")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid MediaCategoryParam param) {
        sysMediaCategoryService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改媒体分类")
    @Parameter(name = "id", required = true, description = "媒体分类id")
    @SysLog("修改媒体分类")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") @NotNull Long id,
                                  @RequestBody @Valid MediaCategoryParam param) {
        sysMediaCategoryService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除媒体分类")
    @Parameter(name = "id", required = true, description = "媒体分类id")
    @SysLog("删除媒体分类")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") @NotNull Long id) {
        sysMediaCategoryService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "查询媒体分类树")
    @GetMapping("/tree")
    public CommonResult<?> tree() {
        return CommonResult.success(sysMediaCategoryService.getTree());
    }
}
