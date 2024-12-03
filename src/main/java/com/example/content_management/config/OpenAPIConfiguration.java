package com.example.content_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI contentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Content Management API")
                        .description("This API allows to manage content in the system")
                        .version("1.0.0"));
    }
}
