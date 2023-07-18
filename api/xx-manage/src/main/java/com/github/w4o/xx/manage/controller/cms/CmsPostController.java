package com.github.w4o.xx.manage.controller.cms;

import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.manage.param.cms.post.AddPostParam;
import com.github.w4o.xx.manage.param.cms.post.ModifyPostParam;
import com.github.w4o.xx.manage.param.cms.post.PostPageParam;
import com.github.w4o.xx.manage.service.CmsPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.github.w4o.xx.core.entity.CmsPostEntity.STATUS_DRAFT;
import static com.github.w4o.xx.core.entity.CmsPostEntity.STATUS_PUBLISH;

/**
 * 文章控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cms/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "30. 文章管理")
public class CmsPostController {

    private final CmsPostService cmsPostService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<?> findPage(@ParameterObject @ModelAttribute PostPageParam param) {
        return CommonResult.success(cmsPostService.getPageList(param));
    }

    @Operation(summary = "添加文章")
    @PostMapping
    public CommonResult<?> add(@RequestBody @Valid AddPostParam param) {
        cmsPostService.add(param);
        return CommonResult.success();
    }

    @Operation(summary = "修改文章")
    @PutMapping("/{postId}")
    public CommonResult<?> modify(@PathVariable("postId") @NotNull Long postId,
                                  @RequestBody @Valid ModifyPostParam param) {
        cmsPostService.update(postId, param);
        return CommonResult.success();
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/{postId}")
    public CommonResult<?> delete(@PathVariable("postId") @NotNull Long postId) {
        cmsPostService.delete(postId);
        return CommonResult.success();
    }

    @Operation(summary = "文章详细信息")
    @GetMapping("/{postId}")
    public CommonResult<?> detail(@PathVariable("postId") @NotNull Long postId) {
        return CommonResult.success(cmsPostService.detail(postId));
    }

    @Operation(summary = "文章标记为发布")
    @PutMapping("/{postId}/publish")
    public CommonResult<?> modifyPublish(@PathVariable("postId") @NotNull Long postId) {
        cmsPostService.updateStatus(postId, STATUS_PUBLISH);
        return CommonResult.success();
    }

    @Operation(summary = "文章标记为草稿")
    @PutMapping("/{postId}/draft")
    public CommonResult<?> modifyDraft(@PathVariable("postId") @NotNull Long postId) {
        cmsPostService.updateStatus(postId, STATUS_DRAFT);
        return CommonResult.success();
    }

}
