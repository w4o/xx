package com.github.w4o.xx.manage.param.sys.dict;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改字典类型请求参数
 *
 * @author Frank
 */
@Data
public class ModifyDictTypeParam {
    @NotBlank
    private String name;
}
