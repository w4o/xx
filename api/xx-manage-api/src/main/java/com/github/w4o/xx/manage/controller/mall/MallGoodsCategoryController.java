package com.github.w4o.xx.manage.controller.mall;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.MallGoodsCategoryEntity;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.mall.category.AddGoodsCategoryParam;
import com.github.w4o.xx.manage.domain.param.mall.category.GoodsCategoryPageParam;
import com.github.w4o.xx.service.impl.mall.MallGoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/goodsCategory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "20. 商品分类管理")
public class MallGoodsCategoryController extends BaseController {

    private final MallGoodsCategoryService mallGoodsCategoryService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<Map<String, Object>>> findPage(GoodsCategoryPageParam param) {
        MallGoodsCategoryEntity entity = new MallGoodsCategoryEntity();
        return CommonResult.page(mallGoodsCategoryService.getPageList(getIPage(param), entity));
    }

    @Operation(summary = "添加商品分类")
    @PostMapping
    public CommonResult<Void> add(@RequestBody AddGoodsCategoryParam param) {
        MallGoodsCategoryEntity entity = new MallGoodsCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setEnabled(false);

        mallGoodsCategoryService.add(entity);
        return CommonResult.success();
    }

    @Operation(summary = "修改商品分类")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable Long id,
                                     @RequestBody AddGoodsCategoryParam param) {
        MallGoodsCategoryEntity entity = new MallGoodsCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(id);
        mallGoodsCategoryService.update(entity);
        return CommonResult.success();
    }

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        mallGoodsCategoryService.removeById(id);
        return CommonResult.success();
    }

    @Operation(summary = "启用商品分类")
    @PutMapping("/{id}/enable")
    public CommonResult<Void> enable(@PathVariable Long id) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryService.getById(id);
        AssertUtils.notNull(goodsCategory);
        goodsCategory.setEnabled(true);
        mallGoodsCategoryService.updateById(goodsCategory);
        return CommonResult.success();
    }

    @Operation(summary = "禁用商品分类")
    @PutMapping("/{id}/disable")
    public CommonResult<Void> disable(@PathVariable Long id) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryService.getById(id);
        AssertUtils.notNull(goodsCategory);
        goodsCategory.setEnabled(false);
        mallGoodsCategoryService.updateById(goodsCategory);
        return CommonResult.success();
    }
}
