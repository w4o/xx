package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.CmsCategoryEntity;
import com.github.w4o.xx.manage.dto.cms.category.CategoryDTO;
import com.github.w4o.xx.manage.param.cms.category.CategoryPageParam;
import com.github.w4o.xx.manage.param.cms.category.CategoryParam;
import com.github.w4o.xx.manage.vo.cms.category.CategoryVO;

import java.util.List;

/**
 * 文章分类服务接口
 *
 * @author Frank
 */
public interface CmsCategoryService extends BaseService<CmsCategoryEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<CategoryDTO> getPageList(CategoryPageParam param);

    /**
     * 添加分类
     *
     * @param param 请求参数
     */
    CategoryVO add(CategoryParam param);

    /**
     * 修改分类
     *
     * @param id    分类id
     * @param param 请求参数
     */
    void update(long id, CategoryParam param);

    /**
     * 删除分类
     *
     * @param id 分类id
     */
    void delete(long id);

    List<?> getTree();
}
