package com.github.w4o.xx.applet.controller.cms;

import com.github.w4o.xx.applet.domain.vo.cms.PostDetailVO;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.service.impl.cms.CmsPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章 控制器
 *
 * @author Frank
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cms/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "11. 文章接口")
public class PostController {

    private final CmsPostService cmsPostService;

    @Operation(summary = "文章详情")
    @GetMapping("/{postId}")
    public CommonResult<PostDetailVO> detail(@PathVariable("postId") Long postId) {
        CmsPostEntity cmsPost = cmsPostService.getOne(CmsPostEntity.gw()
                .eq(CmsPostEntity::getId, postId)
                .eq(CmsPostEntity::getStatus, CmsPostEntity.STATUS_PUBLISH));
        if (cmsPost == null) {
            throw new CustomException(ErrorCode.E1204);
        }
        return CommonResult.success(PostDetailVO.builder()
                .id(cmsPost.getId())
                .title(cmsPost.getTitle())
                .content(cmsPost.getContent())
                .summary(cmsPost.getSummary())
                .thumbnail(cmsPost.getThumbnail())
                .build());
    }
}
