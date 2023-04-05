package com.github.w4o.xx.manage.dto.sys.user;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import com.github.w4o.xx.manage.dto.sys.role.UserRoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "用户分页数据模型")
public class UserDTO extends BaseDataDTO {

    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "电子邮件")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "昵称")
    private String nickName;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "角色列表")
    private List<UserRoleDTO> roleList;
    @Schema(description = "部门")
    private String deptName;
    @Schema(description = "描述")
    private String description;
}
