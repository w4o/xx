package com.github.w4o.xx.service.impl.cms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.service.dto.cms.tag.TagDTO;
import com.github.w4o.xx.service.mapper.CmsPostTagMapper;
import com.github.w4o.xx.service.mapper.CmsTagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class CmsTagService extends BaseServiceImpl<CmsTagMapper, CmsTagEntity> implements BaseService<CmsTagEntity> {

    private final CmsPostTagMapper cmsPostTagMapper;

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 请求参数
     * @return 分页列表
     */
    public Page<TagDTO> getPageList(Page<CmsTagEntity> page, Map<String, Object> condition) {
        Page<TagDTO> pageList = baseMapper.findPage(page, condition);
        handlePageRecord(pageList);
        pageList.getRecords().forEach(dto -> dto.setPostCount(cmsPostTagMapper.selectCount(new LambdaQueryWrapper<CmsPostTagEntity>()
                .eq(CmsPostTagEntity::getTagId, dto.getTagId()))));
        return pageList;
    }

    /**
     * 根据名字获取前10个标签
     *
     * @param name 标签名字
     * @return 标签列表
     */
    public List<Map<String, Object>> getTop10ByName(String name) {
        return baseMapper.findTop10ByName(name);
    }
}
