package com.github.w4o.xx.manage.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Oss oss;

    @Data
    public static class Jwt {
        @NotBlank
        private String secret;
        @NotNull
        private Integer expire;
    }

    @Data
    public static class Oss {
        @NotBlank
        private String domain;
        @NotBlank
        private String bucket;
        @NotBlank
        private String rootPath;
    }
}
