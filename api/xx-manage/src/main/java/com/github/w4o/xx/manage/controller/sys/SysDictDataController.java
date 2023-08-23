package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.dict.DictDataDTO;
import com.github.w4o.xx.manage.param.sys.dict.AddDictDataParam;
import com.github.w4o.xx.manage.param.sys.dict.DictDataPageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictDataParam;
import com.github.w4o.xx.manage.service.SysDictDataService;
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
public class SysDictDataController {

    private final SysDictDataService sysDictDataService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<DictDataDTO>> findPage(@ParameterObject @ModelAttribute @Valid DictDataPageParam param) {
        return CommonResult.success(sysDictDataService.getPageList(param));
    }

    @Operation(summary = "添加字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("添加字典")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddDictDataParam param) {
        sysDictDataService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("修改字典")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyDictDataParam param) {
        sysDictDataService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除字典")
    @Parameter(name = "id", required = true, description = "字典id")
    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") Long id) {
        sysDictDataService.delete(id);
        return CommonResult.success();
    }

}
