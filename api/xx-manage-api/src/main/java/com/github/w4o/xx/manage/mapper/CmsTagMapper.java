package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.manage.dto.cms.tag.TagDTO;
import com.github.w4o.xx.manage.param.cms.tag.TagPageParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 文章标签表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsTagMapper extends BaseMapper<CmsTagEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<TagDTO> findPage(Page<?> page, @Param("condition") TagPageParam param);

    /**
     * 根据名称获取最多10个标签
     *
     * @param name 标签名称
     * @return 标签列表
     */
    @Select("select id,name from cms_tag where deleted = 0 and name like concat('%',#{name},'%') limit 10")
    List<Map<String, Object>> findTop10ByName(@Param("name") String name);
}
