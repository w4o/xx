package com.github.w4o.xx.applet.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
public class LoginUser {
    private Long userId;
    private String openId;
}
