package com.github.w4o.xx.manage.param.sys.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加字典类型请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加字典类型参数")
public class AddDictTypeParam {
    @NotBlank
    @Schema(title = "类型名")
    private String name;
    @NotBlank
    @Schema(title = "类型标签")
    private String label;
}
