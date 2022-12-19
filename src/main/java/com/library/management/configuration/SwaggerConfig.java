//package com.library.management.configuration;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class SwaggerConfig {
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private ApiKey apiKeys() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private List<SecurityContext> securityContexts() {
//        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
//    }
//
//    private List<SecurityReference> securityReferences() {
//
//        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
//
//        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
//    }
//
//    @Bean
//    public Docket api() {
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContexts())
//                .securitySchemes(Arrays.asList(apiKeys())).select().apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()).build();
//
//    }
//
//    private ApiInfo getInfo() {
//        return new ApiInfo("Library Application",
//                "This will provide the all the catalogue of library. The API will allow the user to sort based on different fields", "1.0", "Terms of Service",
//                new Contact(null, null, null),
//                "License of APIS", "API license URL", Collections.emptyList());
//    };
//}
