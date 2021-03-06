package org.una.aeropuerto;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.aeropuerto.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Seguridad", "Metodos de Seguridad"),
                        new Tag("Usuarios", "Entidad de Usuarios"),
                            new Tag("Servicios", "Entidad de Servicios"),
                                new Tag("Vuelos", "Entidad de Vuelos"),
                                    new Tag("Tipos Servicios", "Entidad de Tipos de Servicio"),
                                        new Tag("Transacciones", "Entidad de Transacciones"),
                                            new Tag("Aerolineas", "Entidad de Aerolineas"),
                                                new Tag("Areas Trabajo", "Entidad de Areas de Trabajo"),
                                                    new Tag("Precios", "Entidad de Precios"),
                                                        new Tag("Aviones", "Entidad de Aviones"),
                                                            new Tag("Horarios", "Entidad de Horarios"),
                                                                new Tag("Hora Marcaje", "Entidad de Horario de Marcaje"),
                                                                    new Tag("Roles", "Entidad de Roles"),
                                                                        new Tag("Parametros sistema", "Entidad de Parametros Sistemas"),
                                                                            new Tag("Areas Trabajo Aviones", "Entidad intermedia de Area de Trabajo y Aviones"),
                                                                                new Tag("Areas Trabajo Usuarios", "Entidad intermedia de Usuarios de Trabajo y Usuarios"),
                                                                                    new Tag("Alertas", "Entidad de Alertas")


                );

    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Aeropuerto",
                "Rest API sobre el Aeropuerto.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}

