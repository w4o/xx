package com.github.w4o.xx.applet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
@Schema(name = "登陆返回")
public class LoginVO {
    private String token;
}
