package com.github.w4o.xx.manage.dto.sys.role;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "角色分页数据模型")
public class RoleDTO extends BaseDataDTO {
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "角色名")
    private String roleName;
    @Schema(description = "角色标识")
    private String roleCode;
    @Schema(description = "是否启用")
    private Boolean enabled;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "菜单ID列表")
    private List<Object> menus;
}
