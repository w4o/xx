package com.github.w4o.xx.manage.param.mall.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Frank
 */
@Data
public class AddGoodsCategoryParam {
    /**
     * 父级分类id
     */
    @NotNull
    private Long parentId = 0L;
    @NotBlank
    private String categoryName;
    @NotNull
    private Integer sort = 0;
}
