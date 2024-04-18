package com.github.w4o.xx.service.impl.applet;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletNavEntity;
import com.github.w4o.xx.service.dto.applet.nav.NavDTO;
import com.github.w4o.xx.service.mapper.AppletNavMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序导航服务接口
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletNavService extends BaseServiceImpl<AppletNavMapper, AppletNavEntity> implements BaseService<AppletNavEntity> {

    /**
     * 获取分页列表
     *
     * @param entity 请求参数
     * @return 分页列表
     */
    public Page<NavDTO> getPageList(Page<AppletNavEntity> page, AppletNavEntity entity) {
        Page<NavDTO> pageList = baseMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }

}
