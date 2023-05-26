package com.github.w4o.xx.manage.param.sys.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

/**
 * 修改用户请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改用户参数")
public class ModifyUserParam {

    @Schema(description = "角色", example = "[1,2,3]")
    private Set<Long> roles;
}
