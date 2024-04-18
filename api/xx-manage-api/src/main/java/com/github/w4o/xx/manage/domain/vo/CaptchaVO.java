package com.github.w4o.xx.manage.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
@Schema(name = "验证码模型")
public class CaptchaVO {
    @Schema(description = "验证码key")
    private String captchaKey;
    @Schema(description = "验证码图片Base64编码")
    private String captchaImg;
}
