package com.github.w4o.xx.manage.controller.cms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.CmsCategoryEntity;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.domain.param.cms.category.CategoryPageParam;
import com.github.w4o.xx.manage.domain.param.cms.category.CategoryParam;
import com.github.w4o.xx.manage.domain.vo.cms.category.CategoryVO;
import com.github.w4o.xx.service.dto.cms.category.CategoryDTO;
import com.github.w4o.xx.service.impl.cms.CmsCategoryService;
import com.github.w4o.xx.service.impl.cms.CmsPostCategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
public class CmsCategoryController extends BaseController {

    private final CmsCategoryService cmsCategoryService;
    private final CmsPostCategoryService cmsPostCategoryService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<CategoryDTO>> findPage(@ParameterObject @ModelAttribute CategoryPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(cmsCategoryService.getPageList(getIPage(param), condition));
    }

    @SysLog("添加分类")
    @Operation(summary = "添加分类")
    @PostMapping
    public CommonResult<CategoryVO> add(@RequestBody @Valid CategoryParam param) {

        boolean exists = cmsCategoryService.exists(CmsCategoryEntity.gw().eq(CmsCategoryEntity::getName, param.getName()));
        if (exists) {
            throw new CustomException(ErrorCode.E1201);
        }
        CmsCategoryEntity entity = new CmsCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        cmsCategoryService.save(entity);

        return CommonResult.success(CategoryVO.builder()
                .categoryId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .thumbnail(entity.getThumbnail())
                .build());
    }

    @SysLog("修改分类")
    @Operation(summary = "修改分类")
    @PutMapping("/{categoryId}")
    public CommonResult<Void> modify(@PathVariable("categoryId") @NotNull Long categoryId,
                                     @RequestBody @Valid CategoryParam param) {

        boolean exists = cmsCategoryService.exists(CmsCategoryEntity.gw()
                .eq(CmsCategoryEntity::getName, param.getName())
                .ne(CmsCategoryEntity::getId, categoryId));
        if (exists) {
            throw new CustomException(ErrorCode.E1201);
        }
        CmsCategoryEntity entity = cmsCategoryService.getById(categoryId);
        BeanUtils.copyProperties(param, entity);
        cmsCategoryService.updateById(entity);

        return CommonResult.success();
    }

    @SysLog("删除分类")
    @Operation(summary = "删除分类")
    @DeleteMapping("/{categoryId}")
    public CommonResult<Void> delete(@PathVariable("categoryId") @NotNull Long categoryId) {
        boolean exists = cmsPostCategoryService.exists(CmsPostCategoryEntity.gw().eq(CmsPostCategoryEntity::getCategoryId, categoryId));
        if (exists) {
            throw new CustomException(ErrorCode.E1203);
        }
        cmsCategoryService.removeById(categoryId);
        return CommonResult.success();
    }

    @Operation(summary = "获取分类树")
    @GetMapping("/tree")
    public CommonResult<List<Tree<Long>>> getTree() {
        return CommonResult.success(cmsCategoryService.getTree());
    }

}
