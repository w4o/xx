package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.AppletNavEntity;
import com.github.w4o.xx.manage.dto.applet.nav.NavDTO;
import com.github.w4o.xx.manage.param.applet.nav.AddNavParam;
import com.github.w4o.xx.manage.param.applet.nav.ModifyNavParam;
import com.github.w4o.xx.manage.param.applet.nav.NavPageParam;

/**
 * 小程序导航服务接口
 *
 * @author Frank
 */
public interface AppletNavService extends BaseService<AppletNavEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<NavDTO> getPageList(NavPageParam param);

    /**
     * 添加导航
     *
     * @param param 请求参数
     */
    void add(AddNavParam param);

    /**
     * 修改导航
     *
     * @param id    导航id
     * @param param 请求参数
     */
    void update(long id, ModifyNavParam param);

    /**
     * 删除导航
     *
     * @param id 导航id
     */
    void delete(long id);
}
