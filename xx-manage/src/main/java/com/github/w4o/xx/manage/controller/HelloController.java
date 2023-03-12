package com.github.w4o.xx.manage.controller;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello 控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloController {

    private final HelloService helloService;

    /**
     * Hello
     */
    @GetMapping("/hello")
    public CommonResult<?> hello() {
        return CommonResult.success(helloService.hello());
    }
}
