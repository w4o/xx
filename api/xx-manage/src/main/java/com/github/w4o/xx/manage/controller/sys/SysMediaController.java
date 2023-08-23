package com.github.w4o.xx.manage.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.dto.sys.media.MediaDTO;
import com.github.w4o.xx.manage.param.sys.media.MediaPageParam;
import com.github.w4o.xx.manage.param.sys.media.MediaParam;
import com.github.w4o.xx.manage.service.SysMediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sys/media")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "13. 媒体管理")
public class SysMediaController {

    private final SysMediaService sysMediaService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<Page<MediaDTO>> findPage(@ParameterObject @ModelAttribute MediaPageParam param) {
        return CommonResult.success(sysMediaService.getPageList(param));
    }

    @Operation(summary = "修改媒体信息")
    @SysLog("修改媒体信息")
    @PutMapping("/{id}")
    public CommonResult<?> modify(@PathVariable Long id, @RequestBody MediaParam param) {
        sysMediaService.update(id, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除媒体信息")
    @SysLog("删除媒体信息")
    @DeleteMapping("/{id}")
    public CommonResult<?> delete(@PathVariable Long id) {
        sysMediaService.removeById(id);
        return CommonResult.success();
    }


}
