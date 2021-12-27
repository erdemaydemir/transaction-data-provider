package com.adesso.eaydemir.eth.tdp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openApi = new OpenAPI();
        openApi.info((new Info()).title(this.applicationName).description("Documenting " + this.applicationName + "  REST API with SpringDoc and OpenAPI 3 spec").version("1.0.0").contact((new Contact()).name("Info").url("https://adesso.com.tr/").email("erdem.aydemir@adesso.com.tr")));
        Server server = new Server();
        server.setUrl("/");
        openApi.servers(List.of(server));
        return openApi;
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder().group("api").pathsToMatch("/api/**").build();
    }
}
