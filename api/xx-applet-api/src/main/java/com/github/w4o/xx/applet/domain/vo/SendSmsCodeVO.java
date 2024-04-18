package com.github.w4o.xx.applet.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
public class SendSmsCodeVO {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(description = "测试环境直接返回验证吗，生产环境无此字段")
    private String code;
    @Schema(description = "是否成功发送")
    private Boolean success;
}
