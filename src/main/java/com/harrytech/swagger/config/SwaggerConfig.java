package com.harrytech.swagger.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Provides the configuration needed to setup automatic swagger api documentation
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableWebMvc
@RequiredArgsConstructor
public class SwaggerConfig implements WebMvcConfigurer {

    private final SystemConfig systemConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Configuration of the docket bean which specifies which end points and paths to expose
     *
     * @return Swagger api details
     */
    @Bean
    public Docket api() {
        Predicate<String> allowedPaths =
                "\"*\"".equalsIgnoreCase(systemConfig.getRestrictionpath()) ? PathSelectors.any()::apply : PathSelectors
                        .ant(systemConfig.getRestrictionpath())::apply;

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .directModelSubstitute(LocalTime.class, String.class)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(allowedPaths::test)
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Information on the api
     *
     * @return Information about the api
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                systemConfig.getAppName(),
                systemConfig.getAppDescription(),
                systemConfig.getAppVersion(),
                systemConfig.getAppTos(),
                new Contact(
                        systemConfig.getAppContactName(),
                        systemConfig.getAppContactUrl(),
                        systemConfig.getAppContactEmail()
                ),
                systemConfig.getAppLicense(), systemConfig.getAppLicenseUrl(), Collections.emptyList()
        );
    }
}
