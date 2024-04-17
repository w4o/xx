package com.github.w4o.xx.applet.domain.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Frank
 */
@Data
public class SendSmsCodeParam {
    @NotBlank
    private String phone;
}
