package com.github.w4o.xx.manage.controller.sys;

import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.sys.dict.AddDictDataParam;
import com.github.w4o.xx.manage.param.sys.dict.DictDataPageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictDataParam;
import com.github.w4o.xx.manage.service.SysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
public class SysDictDataController {

    private final SysDictDataService sysDictDataService;

    /**
     * 分页查询
     */
    @GetMapping
    public CommonResult<?> findPage(@Valid DictDataPageParam param) {
        return CommonResult.success(sysDictDataService.getPageList(param));
    }

    /**
     * 添加字典
     */
    @SysLog("添加字典")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddDictDataParam param) {
        sysDictDataService.add(param);
        return CommonResult.success();
    }

    /**
     * 修改字典信息
     */
    @SysLog("修改字典")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable("id") Long id,
                                  @RequestBody @Valid ModifyDictDataParam param) {
        sysDictDataService.update(id, param);
        return CommonResult.success();
    }

    /**
     * 删除字典
     */
    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable("id") Long id) {
        sysDictDataService.delete(id);
        return CommonResult.success();
    }

}
