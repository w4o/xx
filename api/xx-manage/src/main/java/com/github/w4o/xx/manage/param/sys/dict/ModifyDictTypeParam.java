package com.github.w4o.xx.manage.param.sys.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 修改字典类型请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改字典类型参数")
public class ModifyDictTypeParam {
    @NotBlank
    @Schema(description = "字典类型名", example = "性别")
    private String name;
}
