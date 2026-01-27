package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TurmaBuscarDTO {

    @Schema(description = "Filtrar pelo nome da turma")
    private String nome;

    @Schema(description = "Filtrar pelo turno")
    private String turno;

    @Schema(description = "Número da página (começa em 0)", defaultValue = "0")
    private Integer pagina = 0;

    @Schema(description = "Quantidade de itens por página", defaultValue = "10")
    private Integer tamanho = 10;
}