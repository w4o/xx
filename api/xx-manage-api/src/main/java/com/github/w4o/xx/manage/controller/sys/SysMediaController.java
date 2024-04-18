package com.github.w4o.xx.manage.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.sys.media.MediaPageParam;
import com.github.w4o.xx.manage.domain.param.sys.media.MediaParam;
import com.github.w4o.xx.service.dto.sys.media.MediaDTO;
import com.github.w4o.xx.service.impl.sys.SysMediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/sys/media")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "13. 媒体管理")
public class SysMediaController extends BaseController {

    private final SysMediaService sysMediaService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<MediaDTO>> findPage(@ParameterObject @ModelAttribute MediaPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(sysMediaService.getPageList(getIPage(param), condition));
    }

    @Operation(summary = "修改媒体信息")
    @SysLog("修改媒体信息")
    @PutMapping("/{id}")
    public CommonResult<Void> modify(@PathVariable Long id, @RequestBody MediaParam param) {
        SysMediaEntity entity = sysMediaService.getById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        BeanUtils.copyProperties(param, entity);
        sysMediaService.updateById(entity);
        return CommonResult.success();
    }

    @Operation(summary = "删除媒体信息")
    @SysLog("删除媒体信息")
    @DeleteMapping("/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        sysMediaService.removeById(id);
        return CommonResult.success();
    }


}
