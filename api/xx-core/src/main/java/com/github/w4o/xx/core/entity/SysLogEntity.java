package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 操作日志表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_log")
public class SysLogEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * 成功
     */
    private Boolean succeed;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 浏览器UA信息
     */
    private String ua;

    private LocalDateTime createTime;
}
