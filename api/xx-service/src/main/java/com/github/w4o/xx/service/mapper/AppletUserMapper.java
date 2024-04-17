package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.AppletUserEntity;
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
}
