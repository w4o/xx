package com.github.w4o.xx.manage.param.sys.dict;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加字典类型请求参数
 *
 * @author Frank
 */
@Data
public class AddDictTypeParam {
    @NotBlank
    private String name;
    @NotBlank
    private String label;
}
