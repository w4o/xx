package com.github.w4o.xx.manage.param.sys.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改字典数据请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改字典参数")
public class ModifyDictDataParam {

    @NotBlank
    @Schema(description = "字典数据值")
    private String value;

    @Schema(description = "描述")
    private String description;

    @NotNull
    @Schema(description = "排序", defaultValue = "0")
    private Integer sort = NumberUtils.INTEGER_ZERO;

    @Schema(description = "备注")
    private String remark;
}
