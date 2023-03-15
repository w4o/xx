package com.github.w4o.xx.core.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@Schema(name = "通用数据模型")
public class BaseDataDTO {

    @JsonIgnore
    @Schema(description = "创建人ID", hidden = true)
    private Long createBy;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @JsonIgnore
    @Schema(description = "更新人ID", hidden = true)
    private Long lastUpdateBy;
    @Schema(description = "更新人")
    private String lastUpdateUser;
    @Schema(description = "更新时间")
    private LocalDateTime lastUpdateTime;

}
