package com.github.w4o.xx.core.config;

import com.github.w4o.xx.core.filter.HttpTraceLogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HttpTrace配置
 *
 * @author Frank
 */
@Configuration
@ConditionalOnWebApplication
public class HttpTraceConfig {
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    static class ServletTraceFilterConfiguration {
        @Bean
        public HttpTraceLogFilter httpTraceLogFilter() {
            return new HttpTraceLogFilter();
        }
    }
}
