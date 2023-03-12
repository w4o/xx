package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;
import com.github.w4o.xx.manage.service.SysLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/log")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(LogPageParam param) {
        return CommonResult.success(sysLogService.getPageList(param));
    }

    /**
     * 清除操作日志
     */
    @DeleteMapping("/clean")
    public CommonResult<?> clean() {
        sysLogService.clean();
        return CommonResult.success();
    }

}
