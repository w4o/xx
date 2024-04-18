package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.manage.domain.param.sys.log.LoginLogPageParam;
import com.github.w4o.xx.service.dto.sys.log.LoginLogPageDTO;
import com.github.w4o.xx.service.impl.sys.SysLoginLogService;
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
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/loginLog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "12. 登录日志管理")
public class SysLoginLogController extends BaseController {

    private final SysLoginLogService sysLoginLogService;

    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/findPage")
    public CommonResult<PageResult<LoginLogPageDTO>> findPage(@ParameterObject @ModelAttribute LoginLogPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(sysLoginLogService.getPageList(getIPage(param), condition));
    }

    /**
     * 清除登录日志
     */
    @Operation(summary = "清除登录日志")
    @DeleteMapping("/clean")
    public CommonResult<Void> clean() {
        sysLoginLogService.clean();
        return CommonResult.success();
    }

}
