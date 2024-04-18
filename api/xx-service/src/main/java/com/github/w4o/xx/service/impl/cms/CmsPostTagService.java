package com.github.w4o.xx.service.impl.cms;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.service.mapper.CmsPostTagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章标签关系服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsPostTagService extends BaseServiceImpl<CmsPostTagMapper, CmsPostTagEntity> implements BaseService<CmsPostTagEntity> {

}
