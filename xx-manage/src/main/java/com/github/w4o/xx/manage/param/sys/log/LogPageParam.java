package com.github.w4o.xx.manage.param.sys.log;

import com.github.w4o.xx.core.base.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogPageParam extends BasePageParam {
    private String userName;
    private String startDate;
    private String endDate;
}
