package com.github.w4o.xx.manage.domain.param.sys.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 添加字典类型请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加字典类型参数")
public class AddDictTypeParam {
    @NotBlank
    @Schema(description = "类型名", example = "性别")
    private String name;
    @NotBlank
    @Schema(description = "类型标签", example = "GENDER")
    private String label;
}
