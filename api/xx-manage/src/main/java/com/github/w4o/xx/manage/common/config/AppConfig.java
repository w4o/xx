package com.github.w4o.xx.manage.common.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 应用配置
 *
 * @author Frank
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
@Validated
public class AppConfig {

    @NotNull
    private String version;

    @NotNull
    private Jwt jwt;
    @NotBlank
    private String aesKey;

    @Data
    public static class Jwt {
        @NotBlank
        private String secret;
        @NotNull
        private Integer expire;
    }

}
