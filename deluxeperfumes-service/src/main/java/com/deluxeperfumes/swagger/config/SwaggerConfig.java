package com.deluxeperfumes.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI api() {
    final String securitySchemeName = "Bearer Authentication";
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement()
            .addList(securitySchemeName).addList(""))
        .components(new Components()
            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .info(new Info().title("DeluxePerfumes API")
            .description("PS application")
            .version("V0.0.1")
            .license(new License().name("License name: Deluxe Perfumes - JWT")))
        .externalDocs(new ExternalDocumentation()
            .description("PS API Documentation"));
  }

  @Bean
  public GroupedOpenApi groupPublicApis() {
    return GroupedOpenApi.builder()
        .group("public-apis")
        .pathsToMatch("/user/login", "/user/register")
        .build();
  }

  @Bean
  public GroupedOpenApi groupPrivateApis() {
    return GroupedOpenApi.builder()
        .group("private-apis")
        .pathsToMatch("/**")
        .pathsToExclude("/user/login", "/user/register")
        .build();
  }

}