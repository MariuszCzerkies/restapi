package com.example.restapi.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2 // w propertisach dodałem spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
//http://localhost:8080/swagger-ui.html
public class Config {
    /**
     * znika ze strony swaggera basic-error-controller
     **/
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .build()
                .securitySchemes(singletonList(createSchema()))//do konfiguracji Swaggera, aby skonfigurować Token
                .securityContexts(singletonList(createContext()));//do konfiguracji Swaggera, aby skonfigurować Token
    }

    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createRef())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> createRef() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("apiKey", authorizationScopes));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

   /* @Autowired
    private ObjectMapper objectMapper;

    void customizeObjectMapper() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }*/
}
