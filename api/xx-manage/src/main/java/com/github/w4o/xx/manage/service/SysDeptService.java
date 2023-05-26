package com.github.w4o.xx.manage.service;


import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysDeptEntity;
import com.github.w4o.xx.manage.param.sys.dept.AddDeptParam;
import com.github.w4o.xx.manage.param.sys.dept.ModifyDeptParam;

import java.util.List;

/**
 * 部门服务接口
 *
 * @author Frank
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

    /**
     * 添加部门
     *
     * @param param 请求参数
     */
    void add(AddDeptParam param);

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    void delete(Long id);

    /**
     * 修改部门信息
     *
     * @param id    部门id
     * @param param 请求参数
     */
    void update(Long id, ModifyDeptParam param);

    /**
     * 查询部门树
     *
     * @return 部门树
     */
    List<?> findTree();

}
