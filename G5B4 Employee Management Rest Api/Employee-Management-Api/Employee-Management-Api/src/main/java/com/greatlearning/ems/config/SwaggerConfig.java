package com.greatlearning.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket emsAPI() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(ApiInfo()).groupName("Employee Managment System - Rest API")
				.select().apis(RequestHandlerSelectors.basePackage("com.greatlearning.ems.controller")).build();
	}

	public ApiInfo ApiInfo() {
		return new ApiInfoBuilder().title(" Employee Managment System Rest API")
				.description("Employee Management System Rest Api based Web application, "
						+ "where we will be developing CRUD(Create, Read, Update and Delete) "
						+ "functionality along with Sorting,Searching and imlementation of Basic Auth and basic Security")
				.version("1.0").build();
	}

}
