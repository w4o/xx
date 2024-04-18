package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.AppletUserEntity;
import com.github.w4o.xx.service.dto.applet.user.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Frank
 */
public interface AppletUserMapper extends BaseMapper<AppletUserEntity> {

    /**
     * 根据openId查询用户
     *
     * @param openId openId
     * @return 小程序用户实体
     */
    @Select("select * from applet_user where open_id = #{openId} limit 1")
    AppletUserEntity selectByOpenId(String openId);

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页数据
     */
    Page<UserDTO> findPage(Page<AppletUserEntity> page, @Param("condition") AppletUserEntity entity);
}
