package com.github.w4o.xx.manage.param.sys.dict;


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

public class AddDictDataParam {

    @NotNull
    private Long dictTypeId;

    /**
     * 数据值
     */
    @NotBlank
    private String value;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort = NumberUtils.INTEGER_ZERO;

    /**
     * 备注
     */
    private String remark;
}
