package com.github.w4o.xx.manage.domain.param.sys.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 修改字典数据请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改字典参数")
public class ModifyDictDataParam {

    @NotBlank
    @Schema(description = "字典数据值", example = "1")
    private String value;

    @Schema(description = "描述", example = "描述信息xxx")
    private String description;

    @NotNull
    @Schema(description = "排序", defaultValue = "0", example = "0")
    private Integer sort = NumberUtils.INTEGER_ZERO;

    @Schema(description = "备注", example = "备注信息xxx")
    private String remark;
}
