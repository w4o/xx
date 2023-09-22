package com.github.w4o.xx.applet.service.impl;

import com.github.w4o.xx.applet.mapper.CmsPostMapper;
import com.github.w4o.xx.applet.service.CmsPostService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsPostServiceImpl extends BaseServiceImpl<CmsPostMapper, CmsPostEntity> implements CmsPostService {

    @Override
    public Map<String, Object> detail(Long postId) {
        return baseMapper.getDetailById(postId);
    }
}
