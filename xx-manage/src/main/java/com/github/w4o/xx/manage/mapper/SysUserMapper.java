package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.manage.dto.sys.user.UserPageDTO;
import com.github.w4o.xx.manage.param.sys.user.UserPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户 Mapper 接口
 *
 * @author Frank
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<UserPageDTO> findPage(Page<?> page, @Param("condition") UserPageParam param);
}
