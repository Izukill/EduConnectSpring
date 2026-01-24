package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProfessorBuscarDTO {

    @Schema(description = "Filtra pelo nome do professor")
    private String nome;

    @Schema(description = "Filtra pelo email exato")
    private String email;

    @Schema(description = "Filtra pela faixa de salário mínima")
    private Float salarioMinimo;

    @Schema(description = "Número da página (começa em 0).")
    private Integer pagina = 0;

    @Schema(description = "Quantidade de itens por página.")
    private Integer tamanho = 10;
}