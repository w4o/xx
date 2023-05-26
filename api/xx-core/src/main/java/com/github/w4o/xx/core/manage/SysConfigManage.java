package com.github.w4o.xx.core.manage;

/**
 * 系统配置组件接口
 *
 * @author Frank
 */
public interface SysConfigManage {
    /**
     * 根据标签获取配置值
     *
     * @param label 标签
     * @return 配置值
     */
    String getStringConfigValue(String label);
}
