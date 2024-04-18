package com.github.w4o.xx.manage.controller.applet;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.manage.domain.param.applet.user.UserPageParam;
import com.github.w4o.xx.service.dto.applet.user.UserDTO;
import com.github.w4o.xx.service.impl.applet.AppletUserService;
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
public class AppletUserController extends BaseController {

    private final AppletUserService appletUserService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<UserDTO>> findPage(@ParameterObject @ModelAttribute UserPageParam param) {
        AppletUserEntity entity = new AppletUserEntity();
        return CommonResult.page(appletUserService.getPageList(getIPage(param), entity));
    }
}
