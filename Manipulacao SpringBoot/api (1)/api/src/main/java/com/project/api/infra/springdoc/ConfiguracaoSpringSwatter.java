package com.project.api.infra.springdoc;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfiguracaoSpringSwatter {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()
                .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("API do Math")
                        .description("API Rest para aplicações de CRUD")
                        .contact(new Contact().name("Time Backend").email("tue@cudeapito.com"))
                        .license(new License().name("Apache 2.0").url("http://voll.med/api/licenca")));
    }


}
