package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.constant.Constant;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysDictDataEntity;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.dict.AddDictDataParam;
import com.github.w4o.xx.manage.domain.param.sys.dict.DictDataPageParam;
import com.github.w4o.xx.manage.domain.param.sys.dict.ModifyDictDataParam;
import com.github.w4o.xx.service.dto.sys.dict.DictDataDTO;
import com.github.w4o.xx.service.impl.sys.SysDictDataService;
import com.github.w4o.xx.service.impl.sys.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
 * 系统字典数据控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/dictData")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "08. 字典管理")
public class SysDictDataController extends BaseController {

    private final SysDictDataService sysDictDataService;
    private final SysDictTypeService sysDictTypeService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<DictDataDTO>> findPage(@ParameterObject @ModelAttribute @Valid DictDataPageParam param) {
        SysDictDataEntity entity = new SysDictDataEntity();
        entity.setSysDictTypeId(param.getDictTypeId());
        return CommonResult.success(sysDictDataService.getPageList(getIPage(param), entity));
    }

    @Operation(summary = "添加字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("添加字典")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddDictDataParam param) {
        // 获取字典类型数据
        SysDictTypeEntity querySysDictType = sysDictTypeService.getById(param.getDictTypeId());
        AssertUtils.notNull(querySysDictType);

        SysDictDataEntity sysDictEntity = new SysDictDataEntity();
        BeanUtils.copyProperties(param, sysDictEntity);
        sysDictEntity.setLabel(querySysDictType.getLabel());
        sysDictEntity.setSysDictTypeId(param.getDictTypeId());
        sysDictDataService.save(sysDictEntity);
        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
        return CommonResult.success();
    }

    @Operation(summary = "修改字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("修改字典")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") Long id,
                                     @RequestBody @Valid ModifyDictDataParam param) {
        SysDictDataEntity queryEntity = sysDictDataService.getById(id);
        // 判断数据是否存在
        AssertUtils.notNull(queryEntity);
        BeanUtils.copyProperties(param, queryEntity);
        sysDictDataService.updateById(queryEntity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
        return CommonResult.success();
    }

    @Operation(summary = "删除字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") Long id) {
        sysDictDataService.removeById(id);
        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
        return CommonResult.success();
    }

}
