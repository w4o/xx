package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "applet_user")
public class AppletUserEntity extends BaseEntity {
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headImgUrl;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 微信unionId
     */
    private String unionId;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 推荐人
     */
    private Long referrer;
    private LocalDateTime createTime;
    private String lastLoginIp;
    private LocalDateTime lastLoginTime;
}
