package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.manage.dto.sys.user.UserDTO;
import com.github.w4o.xx.manage.param.ChangePasswordParam;
import com.github.w4o.xx.manage.param.sys.user.AddUserParam;
import com.github.w4o.xx.manage.param.sys.user.ModifyUserParam;
import com.github.w4o.xx.manage.param.sys.user.UserPageParam;

/**
 * 系统用户服务接口
 *
 * @author Frank
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    /**
     * 添加用户
     *
     * @param param 请求参数
     */
    void add(AddUserParam param);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 修改用户
     *
     * @param id    用户id
     * @param param 请求参数
     */
    void update(Long id, ModifyUserParam param);

    /**
     * 禁用用户
     *
     * @param id 用户id
     */
    void disable(Long id);

    /**
     * 启用用户
     *
     * @param id 用户id
     */
    void enable(Long id);

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<UserDTO> getPageList(UserPageParam param);

    /**
     * 重置密码
     *
     * @param userId 用户id
     */
    void resetPassword(long userId);

    /**
     * 修改密码
     *
     * @param param 请求参数
     */
    void changePassword(ChangePasswordParam param);
}
