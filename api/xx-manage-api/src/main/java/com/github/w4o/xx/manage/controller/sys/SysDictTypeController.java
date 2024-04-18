package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.dict.AddDictTypeParam;
import com.github.w4o.xx.manage.domain.param.sys.dict.DictTypePageParam;
import com.github.w4o.xx.manage.domain.param.sys.dict.ModifyDictTypeParam;
import com.github.w4o.xx.service.dto.sys.dict.DictTypeDTO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统字典类型控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/dictType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "07. 字典类型管理")
public class SysDictTypeController extends BaseController {

    private final SysDictTypeService sysDictTypeService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<DictTypeDTO>> findPage(@ParameterObject @ModelAttribute DictTypePageParam param) {
        SysDictTypeEntity entity = new SysDictTypeEntity();
        BeanUtils.copyProperties(param, entity);
        return CommonResult.page(sysDictTypeService.getPageList(getIPage(param), entity));
    }

    @Operation(summary = "添加字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("添加字典类型")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid AddDictTypeParam param) {
        if (sysDictTypeService.exists(SysDictTypeEntity.gw().eq(SysDictTypeEntity::getLabel, param.getLabel()))) {
            throw new CustomException(ErrorCode.E1014);
        }
        SysDictTypeEntity sysDictType = new SysDictTypeEntity();
        BeanUtils.copyProperties(param, sysDictType);
        sysDictTypeService.save(sysDictType);
        return CommonResult.success();
    }

    @Operation(summary = "修改字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("修改字典类型")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable("id") Long id,
                                     @RequestBody @Valid ModifyDictTypeParam param) {
        SysDictTypeEntity querySysDictType = sysDictTypeService.getById(id);
        AssertUtils.notNull(querySysDictType);
        querySysDictType.setName(param.getName());
        sysDictTypeService.updateById(querySysDictType);
        return CommonResult.success();
    }

    @Operation(summary = "删除字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("删除字典类型")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable("id") Long id) {
        sysDictTypeService.removeById(id);
        return CommonResult.success();
    }

}
