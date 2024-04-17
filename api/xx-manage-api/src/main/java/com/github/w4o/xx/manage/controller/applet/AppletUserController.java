package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.applet.user.UserPageParam;
import com.github.w4o.xx.manage.service.AppletUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序用户管理控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "41. 小程序用户管理")
public class AppletUserController {

    private final AppletUserService appletUserService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute UserPageParam param) {
        return CommonResult.success(appletUserService.getPageList(param));
    }
}
