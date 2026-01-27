package org.example.config; // Ajuste para seu pacote

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EduConnect API")
                        .description("API REST para gestão escolar (Alunos, Professores e Coordenadores).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Luan Loreto")
                                .url("https://github.com/Izukill/EduConnectSpring")
                                .email("luan.loreto@academico.ifpb.edu.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));
    }
}