package com.github.w4o.xx.applet.controller.mall;

import com.github.w4o.xx.core.base.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "05. 订单接口")
public class OrderController {

    @Operation(summary = "订单列表")
    @GetMapping
    public CommonResult<?> page() {
        return CommonResult.success();
    }

    @Operation(summary = "创建订单")
    @PostMapping
    public CommonResult<?> create() {
        return CommonResult.success();
    }
}
