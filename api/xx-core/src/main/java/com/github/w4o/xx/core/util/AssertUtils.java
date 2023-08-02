package com.github.w4o.xx.core.util;

import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;

/**
 * 断言工具类
 *
 * @author Frank
 */
public class AssertUtils {

    public static void notNull(Object obj) {
        if (obj == null) {
            throw new CustomException(ErrorCode.E404, "空（null）断言触发的数据异常");
        }
    }

    public static void notNull(Object obj, ErrorCode code) {
        if (obj == null) {
            throw new CustomException(code);
        }
    }

    public static void isNull(Object obj, ErrorCode code) {
        if (obj != null) {
            throw new CustomException(code);
        }
    }

    public static void isFalse(boolean expression, ErrorCode code) {
        if (expression) {
            throw new CustomException(code);
        }
    }
}
