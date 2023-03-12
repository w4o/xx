package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.dict.AddDictTypeParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictTypeParam;
import com.github.w4o.xx.manage.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(@RequestParam("pageNo") @NotNull @Min(1) Long pageNo,
                                    @RequestParam("pageSize") @NotNull @Max(100) Long pageSize) {
        return CommonResult.success(sysDictTypeService.getPageList(pageNo, pageSize));
    }

    /**
     * 添加字典类型
     */
    @SysLog("添加字典类型")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddDictTypeParam param) {
        sysDictTypeService.add(param);
        return CommonResult.success();
    }

    /**
     * 修改字典类型
     */
    @SysLog("修改字典类型")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyDictTypeParam param) {
        sysDictTypeService.modify(id, param);
        return CommonResult.success();
    }

    /**
     * 删除字典类型
     */
    @SysLog("删除字典类型")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") Long id) {
        sysDictTypeService.delete(id);
        return CommonResult.success();
    }

}
