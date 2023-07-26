package com.atmosware.soundwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


//@EnableSwagger2
@SpringBootApplication
public class SoundwaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoundwaveApplication.class, args);
    }

    //	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.atmosware.soundwave"))
//				.build();
//	}
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().consumes(Collections.singleton("multipart/form-data")); // Add this line for supporting file uploads }


    }
