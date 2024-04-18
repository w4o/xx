package com.github.w4o.xx.manage.domain.param.sys.user;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "用户分页查询参数")
public class UserPageParam extends BasePageParam {
    @Schema(description = "用户名", example = "admin")
    private String username;
}
