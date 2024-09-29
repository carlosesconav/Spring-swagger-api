package com.preiferia.springcap.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * The type Open api config.
 */
@Configuration
public class OpenAPIConfig {
    @Value("${swaggerapi.openapi.dev-url}")
    private String devUrl;

    /**
     * My open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");
        Contact contact = new Contact();
        contact.setEmail("carlosescobar@gamil.com");
        contact.setName("Carlos Escobar");
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info()
                .title("API Para la gestión de clientes")
                .version("1.0")
                .contact(contact)
                .description("""
                        Esta API expone endpoint para:\s
                        1. Añadir un Cliente\s
                        2. Obtener todos los Clientes
                        3. Actualizar por completo Los datos de un Cliente
                        4. Actualizar solo el estado del Cliente
                        5. Eliminar un Cliente""")
                .license(mitLicense);
        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}