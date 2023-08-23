package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.manage.dto.cms.tag.TagDTO;
import com.github.w4o.xx.manage.param.cms.tag.TagPageParam;
import com.github.w4o.xx.manage.param.cms.tag.TagParam;
import com.github.w4o.xx.manage.vo.cms.tag.TagVO;

import java.util.List;
import java.util.Map;

/**
 * 文章标签服务接口
 *
 * @author Frank
 */
public interface CmsTagService extends BaseService<CmsTagEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<TagDTO> getPageList(TagPageParam param);

    /**
     * 添加标签
     *
     * @param param 请求参数
     */
    TagVO add(TagParam param);

    /**
     * 修改标签
     *
     * @param id    标签id
     * @param param 请求参数
     */
    void update(long id, TagParam param);

    /**
     * 删除标签
     *
     * @param id 标签id
     */
    void delete(long id);

    /**
     * 根据名称获取标签
     *
     * @param name 标签名称
     * @return 标签
     */
    TagVO getByName(String name);

    /**
     * 根据名字获取前10个标签
     *
     * @param name 标签名字
     * @return 标签列表
     */
    List<Map<String, Object>> getTop10ByName(String name);
}
