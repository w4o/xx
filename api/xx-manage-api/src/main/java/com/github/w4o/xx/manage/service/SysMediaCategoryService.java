package com.github.w4o.xx.manage.service;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysMediaCategoryEntity;
import com.github.w4o.xx.manage.param.sys.media.category.MediaCategoryParam;

import java.util.List;

/**
 * 媒体分类服务接口
 *
 * @author Frank
 */
public interface SysMediaCategoryService extends BaseService<SysMediaCategoryEntity> {

    /**
     * 添加媒体分类
     *
     * @param param 请求参数
     */
    void add(MediaCategoryParam param);

    /**
     * 修改媒体分类
     *
     * @param id    主键id
     * @param param 请求参数
     */
    void update(long id, MediaCategoryParam param);

    /**
     * 删除媒体分类
     *
     * @param id 媒体分类Id
     */
    void delete(long id);

    /**
     * 获取媒体分类树
     *
     * @return 媒体分类树
     */
    List<?> getTree();
}
