package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序配置表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "applet_config")
public class AppletConfigEntity extends BaseDataEntity {
    /**
     * 配置Key
     */
    private String configKey;
    /**
     * 配置Value
     */
    private String configValue;
    /**
     * 配置描述
     */
    private String description;
    /**
     * 配置类别 1:文本 2:图片
     */
    private Integer type;
}
