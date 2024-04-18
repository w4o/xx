package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.constant.Constant;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysConfigEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.config.AddConfigParam;
import com.github.w4o.xx.manage.domain.param.sys.config.ConfigPageParam;
import com.github.w4o.xx.manage.domain.param.sys.config.ModifyConfigParam;
import com.github.w4o.xx.service.dto.sys.config.SysConfigDTO;
import com.github.w4o.xx.service.impl.sys.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 系统配置控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/config")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "09. 配置管理")
public class SysConfigController extends BaseController {

    private final SysConfigService sysConfigService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<SysConfigDTO>> findPage(@ParameterObject @ModelAttribute ConfigPageParam param) {
        SysConfigEntity entity = new SysConfigEntity();
        BeanUtils.copyProperties(param, entity);
        return CommonResult.page(sysConfigService.getPageList(getIPage(param), entity));
    }

    @Operation(summary = "添加配置")
    @SysLog("添加配置")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddConfigParam param) {
        // 检查key是否存在
        if (sysConfigService.exists(SysConfigEntity.gw().eq(SysConfigEntity::getConfigKey, param.getConfigKey()))) {
            throw new CustomException(ErrorCode.E1007);
        }
        SysConfigEntity entity = new SysConfigEntity();
        BeanUtils.copyProperties(param, entity);
        sysConfigService.save(entity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));

        return CommonResult.success();
    }

    @Operation(summary = "修改配置")
    @Parameter(name = "id", required = true, description = "配置id")
    @SysLog("修改配置")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") Long id,
                                     @RequestBody @Valid ModifyConfigParam param) {
        SysConfigEntity entity = sysConfigService.getById(id);
        AssertUtils.notNull(entity);
        BeanUtils.copyProperties(param, entity);
        sysConfigService.updateById(entity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
        return CommonResult.success();
    }

    @Operation(summary = "删除配置")
    @Parameter(name = "id", required = true, description = "配置id")
    @SysLog("删除配置")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") @NotNull Long id) {
        sysConfigService.removeById(id);
        return CommonResult.success();
    }

}
