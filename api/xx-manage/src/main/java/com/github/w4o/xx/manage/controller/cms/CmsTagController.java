package com.github.w4o.xx.manage.controller.cms;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.cms.tag.TagPageParam;
import com.github.w4o.xx.manage.param.cms.tag.TagParam;
import com.github.w4o.xx.manage.service.CmsTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 文章标签控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cms/tag")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "32. 标签管理")
public class CmsTagController {

    private final CmsTagService cmsTagService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute TagPageParam param) {
        return CommonResult.success(cmsTagService.getPageList(param));
    }

    @Operation(summary = "添加标签")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid TagParam param) {
        return CommonResult.success(cmsTagService.add(param));
    }

    @Operation(summary = "修改标签")
    @PutMapping("/{tagId}")
    public CommonResult<?> modify(@PathVariable("tagId") @NotNull Long tagId,
                                  @RequestBody @Valid TagParam param) {
        cmsTagService.update(tagId, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("/{tagId}")
    public CommonResult<?> delete(@PathVariable("tagId") @NotNull Long tagId) {
        cmsTagService.delete(tagId);
        return CommonResult.success();
    }

    @Operation(summary = "根据标签名称获取标签信息")
    @GetMapping("/name/{name}")
    public CommonResult<?> getByName(@PathVariable("name") @NotNull String name) {
        return CommonResult.success(cmsTagService.getTop10ByName(name));
    }
}
