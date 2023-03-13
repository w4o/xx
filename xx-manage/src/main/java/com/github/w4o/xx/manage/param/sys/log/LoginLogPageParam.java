package com.github.w4o.xx.manage.param.sys.log;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "登陆日志分页查询参数")
public class LoginLogPageParam extends BasePageParam {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "检索开始日期，格式：yyyy-MM-dd")
    private String startDate;
    @Schema(description = "检索截止日期，格式：yyyy-MM-dd")
    private String endDate;
}
