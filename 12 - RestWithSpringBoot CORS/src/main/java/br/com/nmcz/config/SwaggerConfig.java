package br.com.nmcz.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.com.nmcz"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("unchecked")
	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("RestFul API with Spring Boot 2.1.3", "Some description about your API.", "Version 1",
				"Terms Of Service Url", new Contact("NMCZ JAva", "www.nmcz.com.br", "nilson.andre@nmcz.com.br"),
				"License of API", "License Url", Collections.EMPTY_LIST);
	}

}
