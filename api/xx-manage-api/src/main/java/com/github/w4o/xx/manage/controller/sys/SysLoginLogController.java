package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.log.LoginLogPageParam;
import com.github.w4o.xx.manage.service.SysLoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/loginLog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "12. 登录日志管理")
public class SysLoginLogController {

    private final SysLoginLogService sysLoginLogService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/findPage")
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute LoginLogPageParam param) {
        return CommonResult.success(sysLoginLogService.getPageList(param));
    }

    /**
     * 清除登录日志
     */
    @Operation(summary = "清除登录日志")
    @DeleteMapping("/clean")
    public CommonResult<?> clean() {
        sysLoginLogService.clean();
        return CommonResult.success();
    }

}
