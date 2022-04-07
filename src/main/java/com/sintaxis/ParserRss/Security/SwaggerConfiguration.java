package com.sintaxis.ParserRss.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sintaxis.ParserRss.Controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo("Parser RSS",
                "API parser Rss - Trabajo integrador Sintaxis",
                "1.0",
                "",
                new Contact("Grupo 3","https://github.com/rorro2019/ParserRss","rodrigosebastianzalazar@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList());
    }

}