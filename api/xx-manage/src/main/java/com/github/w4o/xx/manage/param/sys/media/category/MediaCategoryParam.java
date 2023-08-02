package com.github.w4o.xx.manage.param.sys.media.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加/修改媒体分类参数")
public class MediaCategoryParam {
    @NotNull
    private Long parentId = 0L;
    @NotBlank
    private String name;
}
