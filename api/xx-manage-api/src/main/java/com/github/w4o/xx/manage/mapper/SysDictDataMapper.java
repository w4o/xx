package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.mapper.CommonSysDictDataMapper;
import com.github.w4o.xx.manage.dto.sys.dict.DictDataDTO;
import com.github.w4o.xx.manage.param.sys.dict.DictDataPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * 系统字典表 Mapper 接口
 *
 * @author Frank
 */
public interface SysDictDataMapper extends CommonSysDictDataMapper {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<DictDataDTO> findPage(Page<?> page, @Param("condition") DictDataPageParam param);

}
