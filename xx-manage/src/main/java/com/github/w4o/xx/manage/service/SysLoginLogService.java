package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * 系统登陆日志服务接口
 *
 * @author Frank
 */
public interface SysLoginLogService {

    /**
     * 获取分页列表
     *
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return 分页列表
     */
    Page<Map<String, Object>> getPageList(long pageNo, long pageSize);

    /**
     * 清空日志
     */
    void clean();

    /**
     * 添加登录日志
     *
     * @param username 用户名
     */
    void add(String username);
}
