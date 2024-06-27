package com.example.orders.Swagger;



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
                        .addPathItem("/api/orders", new io.swagger.v3.oas.models.PathItem()
                                .get(new Operation()
                                        .summary("Obtencion de todas las ordenes")
                                        .description("Se encarga de traer todos las ordenes"))

                                .post(new Operation()
                                        .summary("Agregar una orden")
                                        .description("Crea un nueva orden")))



                        .addPathItem("/api/orders/{id}", new io.swagger.v3.oas.models.PathItem()

                                .put(new Operation()
                                        .summary("Modificar una orden por su id")
                                        .description("Actualiza una orden por su id"))

                                .delete(new Operation()
                                        .summary("Eliminar una orden por su ID")
                                        .description("Elimina una orden por su ID")

                                )

                        )
                );

    }
}
