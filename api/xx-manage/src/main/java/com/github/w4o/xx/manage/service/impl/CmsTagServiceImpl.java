package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.dto.cms.tag.TagDTO;
import com.github.w4o.xx.manage.mapper.CmsPostTagMapper;
import com.github.w4o.xx.manage.mapper.CmsTagMapper;
import com.github.w4o.xx.manage.param.cms.tag.TagPageParam;
import com.github.w4o.xx.manage.param.cms.tag.TagParam;
import com.github.w4o.xx.manage.service.CmsTagService;
import com.github.w4o.xx.manage.vo.cms.tag.TagVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文章表服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTagEntity> implements CmsTagService {

    private final CmsPostTagMapper cmsPostTagMapper;

    @Override
    public Page<TagDTO> getPageList(TagPageParam param) {
        Page<TagDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        page.getRecords().forEach(dto -> dto.setPostCount(cmsPostTagMapper.selectCount(new LambdaQueryWrapper<CmsPostTagEntity>()
                .eq(CmsPostTagEntity::getTagId, dto.getTagId()))));
        return page;
    }

    @Override
    public TagVO add(TagParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<CmsTagEntity>()
                .eq(CmsTagEntity::getName, param.getName()));
        if (exists) {
            throw new CustomException(ErrorCode.E1200);
        }
        CmsTagEntity entity = new CmsTagEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);

        return TagVO.builder().tagId(entity.getId())
                .name(entity.getName())
                .thumbnail(entity.getThumbnail())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public void update(long id, TagParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<CmsTagEntity>()
                .eq(CmsTagEntity::getName, param.getName())
                .ne(CmsTagEntity::getId, id));
        if (exists) {
            throw new CustomException(ErrorCode.E1200);
        }
        CmsTagEntity entity = baseMapper.selectById(id);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(long id) {
        boolean exists = cmsPostTagMapper.exists(new LambdaQueryWrapper<CmsPostTagEntity>()
                .eq(CmsPostTagEntity::getTagId, id));
        if (exists) {
            throw new CustomException(ErrorCode.E1202);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public TagVO getByName(String name) {
        CmsTagEntity entity = baseMapper.selectOne(new LambdaQueryWrapper<CmsTagEntity>()
                .eq(CmsTagEntity::getName, name));
        if (entity != null) {
            return TagVO.builder().tagId(entity.getId())
                    .name(entity.getName())
                    .thumbnail(entity.getThumbnail())
                    .description(entity.getDescription())
                    .build();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getTop10ByName(String name) {
        return baseMapper.findTop10ByName(name);
    }
}
