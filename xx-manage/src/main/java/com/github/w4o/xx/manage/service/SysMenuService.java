package com.github.w4o.xx.manage.service;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysMenuEntity;
import com.github.w4o.xx.manage.param.sys.menu.MenuParam;

import java.util.List;

/**
 * 系统菜单服务接口
 *
 * @author Frank
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    /**
     * 添加菜单
     *
     * @param param 请求参数
     */
    void add(MenuParam param);

    /**
     * 修改菜单
     *
     * @param id    主键id
     * @param param 请求参数
     */
    void update(long id, MenuParam param);

    /**
     * 删除菜单
     *
     * @param id 菜单Id
     */
    void delete(long id);

    /**
     * 导航菜单树
     *
     * @return 导航菜单树
     */
    List<?> findNavTree();

    /**
     * 菜单树
     *
     * @return 菜单树
     */
    List<?> findTableTree();

    /**
     * 获取菜单树数据
     *
     * @return 菜单树数据
     */
    List<?> getMenuTreeOptions();
}
