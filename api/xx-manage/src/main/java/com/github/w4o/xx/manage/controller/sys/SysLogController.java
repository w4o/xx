package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.log.LogPageDTO;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;
import com.github.w4o.xx.manage.service.SysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "11. 日志管理")
public class SysLogController {

    private final SysLogService sysLogService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<LogPageDTO>> findPage(@ParameterObject @ModelAttribute LogPageParam param) {
        return CommonResult.success(sysLogService.getPageList(param));
    }

    @Operation(summary = "清除操作日志")
    @DeleteMapping("/clean")
    public CommonResult<?> clean() {
        sysLogService.clean();
        return CommonResult.success();
    }

}
