package com.github.w4o.xx.service.impl.applet;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.service.dto.applet.banner.BannerDTO;
import com.github.w4o.xx.service.mapper.AppletBannerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 小程序轮播图服务类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletBannerService extends BaseServiceImpl<AppletBannerMapper, AppletBannerEntity> implements BaseService<AppletBannerEntity> {

    /**
     * 获取banner列表
     *
     * @return banner列表
     */
    public Map<Object, List<Map<String, Object>>> getList() {
        List<Map<String, Object>> list = baseMapper.getBannerList();
        return list.stream().collect(Collectors.groupingBy(m -> m.get("position")));
    }

    /**
     * 获取分页列表
     *
     * @param entity 请求参数
     * @return 分页列表
     */
    public Page<BannerDTO> getPageList(Page<AppletBannerEntity> page, AppletBannerEntity entity) {
        Page<BannerDTO> pageList = baseMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }
}
