package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 登录日志表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_login_log")
public class SysLoginLogEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录状态（online: 在线，登录初始状态，方便统计在线人数；login：退出登录后将online设置为login；logout：退出登录）
     */
    private String status;

    /**
     * IP地址
     */
    private String ip;
    /**
     * 浏览器UA信息
     */
    private String ua;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
