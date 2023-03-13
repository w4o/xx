package com.github.w4o.xx.manage.common.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc 配置
 *
 * @author Frank
 */
@Configuration
public class SpringDocConfig {
    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("xx-service API文档")
                        .description("如有疑问，可在 Github 提交 issue")
                        .version("v1.0.0")
                        .license(new License().name("MIT License").url("https://github.com/w4o/xx-service/blob/main/LICENSE")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
//                        .addSchemas("通用分页模型", new ObjectSchema()
//                                .addProperties("records", new ArraySchema().title("分页数据列表"))
//                                .addProperties("total", new IntegerSchema().title("分页总数"))
//                                .addProperties("size", new IntegerSchema().title("每页显示条数"))
//                                .addProperties("current", new IntegerSchema().title("当前页")))
                        .addSchemas("aaa", ModelConverters.getInstance().resolveAsResolvedSchema(
                                        new AnnotatedType(Page.class).resolveAsRef(false)).schema
                                .addProperties("records", new ArraySchema().title("分页数据列表")))
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                ;
    }

}
