package com.github.w4o.xx.front.controller;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.front.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "01. Hello接口")
public class HelloController {

    private final HelloService helloService;

    @Operation(summary = "Hello")
    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return CommonResult.success(helloService.hello());
    }
}
