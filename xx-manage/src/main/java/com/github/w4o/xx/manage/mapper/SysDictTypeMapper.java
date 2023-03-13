package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.manage.dto.sys.dict.DictTypePageDTO;
import com.github.w4o.xx.manage.param.sys.dict.DictTypePageParam;
import org.apache.ibatis.annotations.Param;

/**
 * 系统字典类型表 Mapper 接口
 *
 * @author Frank
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<DictTypePageDTO> findPage(Page<?> page, @Param("condition") DictTypePageParam param);
}
