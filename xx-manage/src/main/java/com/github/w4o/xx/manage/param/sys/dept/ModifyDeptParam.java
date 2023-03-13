package com.github.w4o.xx.manage.param.sys.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Frank
 */
@Data
@Schema(name = "修改部门参数")
public class ModifyDeptParam {
    @Schema(title = "部门名")
    @NotBlank
    private String deptName;
    @Schema(title = "排序")
    @NotNull
    private Integer sort;
}
