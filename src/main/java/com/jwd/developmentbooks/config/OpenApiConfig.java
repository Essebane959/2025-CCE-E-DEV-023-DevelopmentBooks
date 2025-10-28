package com.jwd.developmentbooks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI developmentBooksApi() {
        return new OpenAPI().info(new Info()
                .title("Development Books API")
                .version("1.0")
                .description("REST API for software-development book checkout and catalog pricing.")
                .contact(new Contact()
                        .name("Jawad Essebane")
                        .email("jawad.essebane@hotmail.com"))
                .license(new License().name("Jawad Essebane Code")));
    }
}
