package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.lang.tree.Tree;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.SysMediaCategoryEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.media.category.MediaCategoryParam;
import com.github.w4o.xx.service.impl.sys.SysMediaCategoryService;
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
@RequestMapping("/sys/media-category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "14. 媒体分类管理")
public class SysMediaCategoryController {

    private final SysMediaCategoryService sysMediaCategoryService;

    @Operation(summary = "添加媒体分类")
    @SysLog("添加媒体分类")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid MediaCategoryParam param) {
        boolean exists = sysMediaCategoryService.exists(SysMediaCategoryEntity.gw()
                .eq(SysMediaCategoryEntity::getName, param.getName())
                .eq(SysMediaCategoryEntity::getParentId, param.getParentId()));
        AssertUtils.isFalse(exists, ErrorCode.E1016);
        SysMediaCategoryEntity entity = new SysMediaCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        sysMediaCategoryService.save(entity);
        return CommonResult.success();
    }

    @Operation(summary = "修改媒体分类")
    @Parameter(name = "id", required = true, description = "媒体分类id")
    @SysLog("修改媒体分类")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") @NotNull Long id,
                                     @RequestBody @Valid MediaCategoryParam param) {
        boolean exists = sysMediaCategoryService.exists(SysMediaCategoryEntity.gw()
                .eq(SysMediaCategoryEntity::getParentId, param.getParentId())
                .eq(SysMediaCategoryEntity::getName, param.getName())
                .ne(SysMediaCategoryEntity::getId, id));
        AssertUtils.isFalse(exists, ErrorCode.E1016);
        SysMediaCategoryEntity entity = sysMediaCategoryService.getById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        BeanUtils.copyProperties(param, entity);
        sysMediaCategoryService.updateById(entity);

        return CommonResult.success();
    }

    @Operation(summary = "删除媒体分类")
    @Parameter(name = "id", required = true, description = "媒体分类id")
    @SysLog("删除媒体分类")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysMediaCategoryService.removeById(id);
        return CommonResult.success();
    }

    @Operation(summary = "查询媒体分类树")
    @GetMapping("/tree")
    public CommonResult<List<Tree<Long>>> tree() {
        return CommonResult.success(sysMediaCategoryService.getTree());
    }
}
