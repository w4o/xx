package com.github.w4o.xx.manage.domain.param.sys.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加部门参数")
public class AddDeptParam {
    @Schema(description = "部门名", example = "技术部")
    @NotBlank
    private String deptName;
    @Schema(description = "上级部门ID", example = "0")
    @NotNull
    private Long parentId = NumberUtils.LONG_ZERO;
    @Schema(description = "排序", example = "0")
    @NotNull
    private Integer sort = NumberUtils.INTEGER_ZERO;
    @Schema(description = "描述", example = "技术部")
    private String description;
}
