package com.github.w4o.xx.manage.dto.sys.role;

import com.github.w4o.xx.core.base.dto.BasePageDataDTO;
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
public class RolePageDTO extends BasePageDataDTO {
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "角色名")
    private String name;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "菜单ID列表")
    private List<Object> menus;
}
