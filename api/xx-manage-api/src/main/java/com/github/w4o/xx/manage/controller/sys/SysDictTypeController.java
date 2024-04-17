package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.dict.DictTypeDTO;
import com.github.w4o.xx.manage.param.sys.dict.AddDictTypeParam;
import com.github.w4o.xx.manage.param.sys.dict.DictTypePageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictTypeParam;
import com.github.w4o.xx.manage.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<DictTypeDTO>> findPage(@ParameterObject @ModelAttribute DictTypePageParam param) {
        return CommonResult.success(sysDictTypeService.getPageList(param));
    }

    @Operation(summary = "添加字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("添加字典类型")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddDictTypeParam param) {
        sysDictTypeService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("修改字典类型")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyDictTypeParam param) {
        sysDictTypeService.modify(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除字典类型")
    @Parameter(name = "id", required = true, description = "字典类型id")
    @SysLog("删除字典类型")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") Long id) {
        sysDictTypeService.delete(id);
        return CommonResult.success();
    }

}
