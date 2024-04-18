package com.github.w4o.xx.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.BasePageParam;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象公共Controller
 *
 * @author Frank
 */
@Setter(onMethod = @__(@Autowired))
public abstract class BaseController {

    // 默认页码： 第一页
    private static final int DEFAULT_PAGE_INDEX = 1;
    // 默认条数： 20
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 获取页码
     */
    protected long getPageIndex(BasePageParam param) {
        if (param.getPageNo() == null) {
            return DEFAULT_PAGE_INDEX;
        }
        return param.getPageNo();
    }

    /**
     * 获取条数
     *
     * @param allowQueryAll 是否允许获取全部数据
     */
    protected long getPageSize(BasePageParam param, boolean allowQueryAll) {
        if (allowQueryAll && param.getPageSize() != null && param.getPageSize() == -1) {
            // -1代表获取全部数据，查询int最大值的数据
            return Integer.MAX_VALUE;
        }

        if (param.getPageSize() == null || param.getPageSize() < 0) {
            return DEFAULT_PAGE_SIZE;
        }
        return param.getPageSize();
    }

    /**
     * 获取Ipage分页信息, 默认不允许获取全部数据
     */
    protected <T> Page<T> getIPage(BasePageParam param) {
        return new Page<>(getPageIndex(param), getPageSize(param, false));
    }

    /**
     * 获取Ipage分页信息, 加入条件：是否允许获取全部数据
     */
    protected <T> Page<T> getIPage(BasePageParam param, boolean allowQueryAll) {
        return new Page<>(getPageIndex(param), getPageSize(param, allowQueryAll));
    }

}
