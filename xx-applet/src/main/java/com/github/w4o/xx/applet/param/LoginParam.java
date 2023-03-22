package com.github.w4o.xx.applet.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank
 */
@Data
@Schema(name = "小程序登陆参数")
public class LoginParam {
    @NotBlank
    @Schema(description = "小程序code")
    private String code;
    @Schema(description = "小程序code2，用于获取手机号")
    private String code2;
    @Schema(description = "推荐人")
    private Long referrer;
}
