package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.manage.dto.applet.banner.BannerDTO;
import com.github.w4o.xx.manage.param.applet.banner.BannerPageParam;
import org.apache.ibatis.annotations.Select;

/**
 * 小程序轮播图表 Mapper 接口
 *
 * @author Frank
 */
public interface AppletBannerMapper extends BaseMapper<AppletBannerEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    @Select("""
            select id as bannerId,
            url,
            title,
            description,
            sort,
            create_by,
            create_time,
            last_update_by,
            last_update_time
            from applet_banner where deleted = 0
            """)
    Page<BannerDTO> findPage(Page<?> page, BannerPageParam param);
}
