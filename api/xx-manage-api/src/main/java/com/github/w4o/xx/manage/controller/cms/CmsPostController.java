package com.github.w4o.xx.manage.controller.cms;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.annotation.SysLog;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.domain.param.cms.post.PostPageParam;
import com.github.w4o.xx.manage.domain.param.cms.post.PostParam;
import com.github.w4o.xx.service.dto.cms.post.PostDTO;
import com.github.w4o.xx.service.impl.cms.CmsPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
public class CmsPostController extends BaseController {

    private final CmsPostService cmsPostService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<PostDTO>> findPage(@ParameterObject @ModelAttribute PostPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(cmsPostService.getPageList(getIPage(param), condition));
    }

    @SysLog("添加文章")
    @Operation(summary = "添加文章")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid PostParam param) {
        CmsPostEntity entity = new CmsPostEntity();
        BeanUtils.copyProperties(param, entity);
        cmsPostService.add(entity, param.getTags(), param.getCategoryIds());
        return CommonResult.success();
    }

    @SysLog("修改文章")
    @Operation(summary = "修改文章")
    @PutMapping("/{postId}")
    public CommonResult<Void> modify(@PathVariable("postId") @NotNull Long postId,
                                     @RequestBody @Valid PostParam param) {
        CmsPostEntity entity = cmsPostService.getById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        BeanUtils.copyProperties(param, entity);
        cmsPostService.update(entity, param.getTags(), param.getCategoryIds());
        return CommonResult.success();
    }

    @SysLog("删除文章")
    @Operation(summary = "删除文章")
    @DeleteMapping("/{postId}")
    public CommonResult<Void> delete(@PathVariable("postId") @NotNull Long postId) {
        cmsPostService.delete(postId);
        return CommonResult.success();
    }

    @Operation(summary = "文章详细信息")
    @GetMapping("/{postId}")
    public CommonResult<PostDTO> detail(@PathVariable("postId") @NotNull Long postId) {
        return CommonResult.success(cmsPostService.detail(postId));
    }

    @SysLog("文章标记为发布")
    @Operation(summary = "文章标记为发布")
    @PutMapping("/{postId}/publish")
    public CommonResult<Void> modifyPublish(@PathVariable("postId") @NotNull Long postId) {
        CmsPostEntity entity = cmsPostService.getById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        entity.setStatus(STATUS_PUBLISH);
        cmsPostService.updateById(entity);
        return CommonResult.success();
    }

    @SysLog("文章标记为草稿")
    @Operation(summary = "文章标记为草稿")
    @PutMapping("/{postId}/draft")
    public CommonResult<Void> modifyDraft(@PathVariable("postId") @NotNull Long postId) {
        CmsPostEntity entity = cmsPostService.getById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        entity.setStatus(STATUS_DRAFT);
        cmsPostService.updateById(entity);
        return CommonResult.success();
    }

}
