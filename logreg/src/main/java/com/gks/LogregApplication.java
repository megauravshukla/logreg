package com.gks;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "com.gks.*" })
@EnableJpaRepositories(basePackages = { "com.gks.repository" })
@EntityScan(basePackages = "com.gks.entity")
@EnableSwagger2
public class LogregApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogregApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// http://localhost:8080/logreg/v2/api-docs Swagger docs
	// http://localhost:8080/logreg/swagger-ui/ swaggerUI

	@Bean
	public ApiInfo apiDetails() {
		return new ApiInfo("Patient Login Registration API", 
				"Sample API For Swagger",
				"1.0",
				"Free To Use",
				new springfox.documentation.service
				.Contact("Gaurav Shukla",
						"abc.com", 
						"kumargaurav03dec@gmail.com"),
						"API Licence ", 
						"abc.com", 
						Collections.emptyList());
	}

	@Bean
	public Docket appApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}

}
