package com.github.w4o.xx.manage.controller.mall;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.MallGoodsEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.mall.goods.AddGoodsParam;
import com.github.w4o.xx.manage.domain.param.mall.goods.GoodsPageParam;
import com.github.w4o.xx.service.impl.mall.MallGoodsService;
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
public class MallGoodsController extends BaseController {

    private final MallGoodsService mallGoodsService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Void> findPage(GoodsPageParam param) {
        return CommonResult.success();
    }

    @Operation(summary = "添加商品")
    @PostMapping
    public CommonResult<Void> add(@RequestBody AddGoodsParam param) {
        return CommonResult.success();
    }

    @Operation(summary = "修改商品")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable Long id,
                                     @RequestBody AddGoodsParam param) {
        return CommonResult.success();
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        return CommonResult.success();
    }

    @Operation(summary = "上架商品")
    @PutMapping("/{id}/onSale")
    public CommonResult<Void> start(@PathVariable Long id) {
        MallGoodsEntity entity = mallGoodsService.getById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        entity.setStatus(STATUS_ON_SALE);
        mallGoodsService.updateById(entity);
        return CommonResult.success();
    }

    @Operation(summary = "下架商品")
    @PutMapping("/{id}/offSale")
    public CommonResult<Void> stop(@PathVariable Long id) {
        MallGoodsEntity entity = mallGoodsService.getById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        entity.setStatus(STATUS_OFF_SALE);
        mallGoodsService.updateById(entity);
        return CommonResult.success();
    }
}
