package com.github.w4o.xx.service.dto.applet.nav;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小程序导航数据模型")
public class NavDTO extends BaseDataDTO {
    @Schema(description = "导航id")
    private Long navId;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "链接/路径")
    private String path;
    @Schema(description = "排序")
    private Integer sort = 0;
}
