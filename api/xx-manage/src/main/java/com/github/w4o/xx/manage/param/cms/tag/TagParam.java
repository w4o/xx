package com.github.w4o.xx.manage.param.cms.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加文章标签参数")
public class TagParam {
    @NotBlank
    @Schema(description = "标签名称")
    private String name;
    @Schema(description = "标签描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
}
