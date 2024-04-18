package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 小程序配置表实体
 *
 * @author Frank
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName(value = "applet_config")
public class AppletConfigEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<AppletConfigEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;

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
