package com.github.w4o.xx.core.base;

/**
 * 通用错误接口
 *
 * @author Frank
 */
public interface CommonError {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
