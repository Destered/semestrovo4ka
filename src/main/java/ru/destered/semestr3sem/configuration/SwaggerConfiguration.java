package ru.destered.semestr3sem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("StackOverCode")
                                .version("1.0.0")
                );
    }

}
