package com.github.w4o.xx.manage.controller.cms;

import cn.hutool.core.bean.BeanUtil;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.base.PageResult;
import com.github.w4o.xx.core.controller.BaseController;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.domain.param.cms.tag.TagPageParam;
import com.github.w4o.xx.manage.domain.param.cms.tag.TagParam;
import com.github.w4o.xx.manage.domain.vo.cms.tag.TagOptionVO;
import com.github.w4o.xx.manage.domain.vo.cms.tag.TagVO;
import com.github.w4o.xx.service.dto.cms.tag.TagDTO;
import com.github.w4o.xx.service.impl.cms.CmsPostTagService;
import com.github.w4o.xx.service.impl.cms.CmsTagService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class CmsTagController extends BaseController {

    private final CmsTagService cmsTagService;
    private final CmsPostTagService cmsPostTagService;

    @Operation(summary = "分页查询")
    @GetMapping
    public CommonResult<PageResult<TagDTO>> findPage(@ParameterObject @ModelAttribute TagPageParam param) {
        Map<String, Object> condition = BeanUtil.beanToMap(param);
        return CommonResult.page(cmsTagService.getPageList(getIPage(param), condition));
    }

    @Operation(summary = "添加标签")
    @PostMapping
    public CommonResult<TagVO> add(@RequestBody @Valid TagParam param) {
        boolean exists = cmsTagService.exists(CmsTagEntity.gw().eq(CmsTagEntity::getName, param.getName()));
        if (exists) {
            throw new CustomException(ErrorCode.E1200);
        }
        CmsTagEntity entity = new CmsTagEntity();
        BeanUtils.copyProperties(param, entity);
        cmsTagService.save(entity);

        return CommonResult.success(TagVO.builder().tagId(entity.getId())
                .name(entity.getName())
                .thumbnail(entity.getThumbnail())
                .description(entity.getDescription())
                .build());
    }

    @Operation(summary = "修改标签")
    @PutMapping("/{tagId}")
    public CommonResult<Void> modify(@PathVariable("tagId") @NotNull Long tagId,
                                     @RequestBody @Valid TagParam param) {
        boolean exists = cmsTagService.exists(CmsTagEntity.gw()
                .eq(CmsTagEntity::getName, param.getName())
                .ne(CmsTagEntity::getId, tagId));
        if (exists) {
            throw new CustomException(ErrorCode.E1200);
        }
        CmsTagEntity entity = cmsTagService.getById(tagId);
        BeanUtils.copyProperties(param, entity);
        cmsTagService.updateById(entity);
        return CommonResult.success();
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("/{tagId}")
    public CommonResult<Void> delete(@PathVariable("tagId") @NotNull Long tagId) {
        boolean exists = cmsPostTagService.exists(CmsPostTagEntity.gw().eq(CmsPostTagEntity::getTagId, tagId));
        if (exists) {
            throw new CustomException(ErrorCode.E1202);
        }
        cmsTagService.removeById(tagId);
        return CommonResult.success();
    }

    @Operation(summary = "根据标签名称获取标签信息")
    @GetMapping("/name/{name}")
    public CommonResult<List<TagOptionVO>> getByName(@PathVariable("name") @NotNull String name) {
        List<Map<String, Object>> list = cmsTagService.getTop10ByName(name);
        List<TagOptionVO> result = new ArrayList<>();
        list.forEach(map -> result.add(TagOptionVO.builder()
                .id((Long) map.get("id"))
                .name((String) map.get("name"))
                .build()));
        return CommonResult.success(result);
    }
}
