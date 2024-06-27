package com.ucc.crudservice.Swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("Api Con Spring")
                .version("1.0 SNAPSHOT")
                .description("Proyecto con crud en spring boot")
                .termsOfService("http://swagger.io/terms/"))
                .paths(new Paths()
                        .addPathItem("/api/products", new io.swagger.v3.oas.models.PathItem()
                                .get(new Operation()
                                        .summary("Obtencion de todos los productos")
                                        .description("Se encarga de traer todos los productos"))

                                .post(new Operation()
                                        .summary("Agregar un producto")
                                        .description("Crea un nuevo producto")))



                        .addPathItem("/api/products/{id}", new io.swagger.v3.oas.models.PathItem()

                                .put(new Operation()
                                        .summary("Modificar un producto por id")
                                        .description("Actualiza un producto por su id"))

                                .delete(new Operation()
                                        .summary("Eliminar producto por su ID")
                                        .description("Elimina un producto por su ID")

                                )

                        )
                );

    }
}
