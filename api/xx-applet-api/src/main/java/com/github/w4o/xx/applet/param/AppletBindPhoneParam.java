package com.github.w4o.xx.applet.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author Frank
 */
@Data
public class AppletBindPhoneParam {
    @NotBlank
    private String phone;
    @NotBlank
    private String smsCode;
}
