package com.github.w4o.xx.manage.param.sys.dict;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加字典数据请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加字典参数")
public class AddDictDataParam {

    @NotNull
    @Schema(description = "字典类型ID", example = "1")
    private Long dictTypeId;

    @NotBlank
    @Schema(description = "字典值", example = "1")
    private String value;

    @Schema(description = "字典类型", example = "1")
    private String type;

    @Schema(description = "字典描述", example = "描述信息xxx")
    private String description;

    @Schema(description = "排序", defaultValue = "0", example = "0")
    private Integer sort = NumberUtils.INTEGER_ZERO;

    @Schema(description = "备注", example = "备注信息xxx")
    private String remark;
}
