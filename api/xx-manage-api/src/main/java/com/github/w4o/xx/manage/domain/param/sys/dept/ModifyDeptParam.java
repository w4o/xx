package com.github.w4o.xx.manage.domain.param.sys.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "修改部门参数")
public class ModifyDeptParam {
    @Schema(description = "部门名", example = "技术部")
    @NotBlank
    private String deptName;
    @Schema(description = "排序", example = "0")
    @NotNull
    private Integer sort;
    @Schema(description = "描述", example = "技术部")
    private String description;
}
