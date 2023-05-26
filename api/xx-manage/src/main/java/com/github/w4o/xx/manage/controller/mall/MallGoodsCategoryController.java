package com.github.w4o.xx.manage.controller.mall;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.mall.category.AddGoodsCategoryParam;
import com.github.w4o.xx.manage.param.mall.category.GoodsCategoryPageParam;
import com.github.w4o.xx.manage.service.MallGoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/goodsCategory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "20. 商品分类管理")
public class MallGoodsCategoryController {

    private final MallGoodsCategoryService mallGoodsCategoryService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(GoodsCategoryPageParam param) {
        return CommonResult.success(mallGoodsCategoryService.getPageList(param));
    }

    @Operation(summary = "添加商品分类")
    @PostMapping
    public CommonResult<?> add(@RequestBody AddGoodsCategoryParam param) {
        mallGoodsCategoryService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改商品分类")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable Long id,
                                  @RequestBody AddGoodsCategoryParam param) {
        mallGoodsCategoryService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除商品分类")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        mallGoodsCategoryService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "启用商品分类")
    @PutMapping("/{id}/enable")
    public CommonResult<?> enable(@PathVariable Long id) {
        mallGoodsCategoryService.enable(id);
        return CommonResult.success();
    }

    @Operation(summary = "禁用商品分类")
    @PutMapping("/{id}/disable")
    public CommonResult<?> disable(@PathVariable Long id) {
        mallGoodsCategoryService.disable(id);
        return CommonResult.success();
    }
}
