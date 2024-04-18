package com.github.w4o.xx.manage.domain.param.sys.log;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "登录日志分页查询参数")
public class LoginLogPageParam extends BasePageParam {
    @Schema(description = "用户名", example = "admin")
    private String username;
    @Schema(description = "检索开始日期，格式：yyyy-MM-dd", pattern = "yyyy-MM-dd", example = "2020-01-01")
    private String startDate;
    @Schema(description = "检索截止日期，格式：yyyy-MM-dd", pattern = "yyyy-MM-dd", example = "2020-01-01")
    private String endDate;
}
