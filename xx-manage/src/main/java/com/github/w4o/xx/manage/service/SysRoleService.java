package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.dto.sys.role.RolePageDTO;
import com.github.w4o.xx.manage.param.sys.role.AddRoleMenuParam;
import com.github.w4o.xx.manage.param.sys.role.AddRoleParam;
import com.github.w4o.xx.manage.param.sys.role.ModifyRoleParam;
import com.github.w4o.xx.manage.param.sys.role.RolePageParam;

import java.util.List;
import java.util.Map;

/**
 * 系统角色服务接口
 *
 * @author Frank
 */
public interface SysRoleService {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<RolePageDTO> getPageList(RolePageParam param);

    /**
     * 添加角色
     *
     * @param param 请求参数
     */
    void add(AddRoleParam param);

    /**
     * 修改角色
     *
     * @param id    角色id
     * @param param 请求参数
     */
    void update(Long id, ModifyRoleParam param);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void delete(Long id);

    /**
     * 查询全部角色
     *
     * @return 角色集合
     */
    List<Map<String, Object>> getAll();

    /**
     * 添加角色菜单
     *
     * @param param 请求参数
     */
    void addRoleMenu(AddRoleMenuParam param);
}
