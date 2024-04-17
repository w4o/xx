package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
public interface AppletBannerMapper extends BaseMapper<AppletBannerEntity> {

    /**
     * 获取banner列表
     *
     * @return banner列表
     */
    @Select("""
            select id, url, path, title, description, sort, type, position from applet_banner
            where deleted = 0
            and visible = 1
            and (start_time is null or start_time <= now())
            and (end_time is null or end_time >= now())
            order by sort desc
            """)
    List<Map<String, Object>> getBannerList();
}
