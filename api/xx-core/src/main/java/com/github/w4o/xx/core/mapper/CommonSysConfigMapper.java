package com.github.w4o.xx.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 系统配置表 Mapper 共通接口
 *
 * @author Frank
 */
public interface CommonSysConfigMapper extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key获取字符串value
     *
     * @param configKey key
     * @return 字符串
     */
    @Select("select config_value from sys_config where config_key = #{configKey}")
    String getStringValue(@Param("configKey") String configKey);

    /**
     * 根据key获取整数value
     *
     * @param configKey key
     * @return 整数
     */
    @Select("select config_value from sys_config where config_key = #{configKey}")
    Integer getIntegerValue(@Param("configKey") String configKey);

}
