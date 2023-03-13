package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.dto.sys.log.LoginLogPageDTO;
import com.github.w4o.xx.manage.param.sys.log.LoginLogPageParam;

/**
 * 系统登陆日志服务接口
 *
 * @author Frank
 */
public interface SysLoginLogService {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<LoginLogPageDTO> getPageList(LoginLogPageParam param);

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
