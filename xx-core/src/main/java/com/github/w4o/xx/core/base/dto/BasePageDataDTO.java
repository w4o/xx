package com.github.w4o.xx.core.base.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@Schema(name = "通用分页数据模型")
public class BasePageDataDTO {

    @Schema(title = "创建人ID")
    private Long createBy;
    @Schema(title = "创建人")
    private String createUser;
    @Schema(title = "创建时间")
    private LocalDateTime createTime;
    @Schema(title = "更新人ID")
    private Long lastUpdateBy;
    @Schema(title = "更新人")
    private String lastUpdateUser;
    @Schema(title = "更新时间")
    private LocalDateTime lastUpdateTime;

}
