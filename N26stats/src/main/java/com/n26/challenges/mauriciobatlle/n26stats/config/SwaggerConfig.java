package com.n26.challenges.mauriciobatlle.n26stats.config;

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
	private ApiInfo n26ApiInfo() {
	     return new ApiInfo(
	       "n26 Service", 
	       "API that provides storage and statistics", 
	       "", 
	       "", 
	       new Contact("Mauricio Batlle", "https://www.linkedin.com/in/mauricioabatlle/", "mauricioacastro@gmail.com"), 
	       "", "", Collections.emptyList());
	}
	
   @Bean
   public Docket api() {

           return new Docket(DocumentationType.SWAGGER_2)  
                   .select()                                  
                   .apis(RequestHandlerSelectors.any())              
                   .paths(PathSelectors.any())                          
                   .build().apiInfo(n26ApiInfo()); } 
   		


}
