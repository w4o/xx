package com.github.w4o.xx.applet.controller.cms;

import com.github.w4o.xx.applet.service.CmsPostService;
import com.github.w4o.xx.core.base.CommonResult;
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
    public CommonResult<?> detail(@PathVariable("postId") Long postId) {
        return CommonResult.success(cmsPostService.detail(postId));
    }
}
