package com.github.w4o.xx.manage.controller.mall;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.mall.goods.AddGoodsParam;
import com.github.w4o.xx.manage.param.mall.goods.GoodsPageParam;
import com.github.w4o.xx.manage.service.MallGoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.w4o.xx.core.entity.MallGoodsEntity.STATUS_OFF_SALE;
import static com.github.w4o.xx.core.entity.MallGoodsEntity.STATUS_ON_SALE;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/goods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "21. 商品管理")
public class MallGoodsController {

    private final MallGoodsService mallGoodsService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(GoodsPageParam param) {
        return CommonResult.success(mallGoodsService.getPageList(param));
    }

    @Operation(summary = "添加商品")
    @PostMapping
    public CommonResult<?> add(@RequestBody AddGoodsParam param) {
        mallGoodsService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改商品")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable Long id,
                                  @RequestBody AddGoodsParam param) {
        mallGoodsService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        mallGoodsService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "上架商品")
    @PutMapping("/{id}/start")
    public CommonResult<?> start(@PathVariable Long id) {
        mallGoodsService.updateStatus(id, STATUS_ON_SALE);
        return CommonResult.success();
    }

    @Operation(summary = "下架商品")
    @PutMapping("/{id}/stop")
    public CommonResult<?> stop(@PathVariable Long id) {
        mallGoodsService.updateStatus(id, STATUS_OFF_SALE);
        return CommonResult.success();
    }
}
