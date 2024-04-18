package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.manage.domain.param.sys.log.LogPageParam;
import com.github.w4o.xx.service.dto.sys.log.LogPageDTO;
import com.github.w4o.xx.service.impl.sys.SysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<LogPageDTO>> findPage(@ParameterObject @ModelAttribute LogPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(sysLogService.getPageList(getIPage(param), condition));
    }

    @Operation(summary = "清除操作日志")
    @DeleteMapping("/clean")
    public CommonResult<Void> clean() {
        sysLogService.clean();
        return CommonResult.success();
    }

}
