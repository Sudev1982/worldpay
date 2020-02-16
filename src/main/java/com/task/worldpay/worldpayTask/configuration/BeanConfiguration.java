package com.task.worldpay.worldpayTask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class BeanConfiguration {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SPRING_WEB.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/oapi/**"))
                .apis(RequestHandlerSelectors.basePackage("com.task.worldpay"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Worldpay Offer api [OAPI]")
                .description("Worldpay Developer Task API")
                .version("1.0.0")
                .build();
    }


}
