//package com.gks.configurations;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SpringFoxConfig {                                    
//    @Bean
//    public Docket swaggerConfiguration() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("com.gks"))              
//          .paths(PathSelectors.ant("/logreg/*"))                          
//          .build()
//          .apiInfo(apiDetails());
//    }
//    
//    
//    @Bean
//    public ApiInfo apiDetails() { 
//        return new ApiInfo("Patent Login Registration API",
//        		"Sample API For Swagger", 
//        		"1.0",
//        		"Free To Use",
//        		new springfox.documentation.service.Contact("Gaurav Shukla", "abc.com", "kumargaurav03dec@gmail.com"),
//        		"API Licence ", 
//        		"abc.com",
//        		Collections.EMPTY_LIST
//          );                                           
//    }
//}