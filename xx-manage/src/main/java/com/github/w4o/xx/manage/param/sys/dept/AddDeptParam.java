package com.github.w4o.xx.manage.param.sys.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加部门参数")
public class AddDeptParam {
    @Schema(title = "部门名")
    @NotBlank
    private String deptName;
    @Schema(title = "上级部门ID", description = "一级部门为0")
    @NotNull
    private Long parentId = NumberUtils.LONG_ZERO;
    @Schema(title = "排序")
    @NotNull
    private Integer sort = NumberUtils.INTEGER_ZERO;
}
