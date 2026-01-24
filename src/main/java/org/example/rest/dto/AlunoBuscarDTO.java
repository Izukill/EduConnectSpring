package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlunoBuscarDTO {

    @Schema(description = "Filtra pelo nome do aluno.")
    private String nome;

    @Schema(description = "Filtra pela matrícula exata.")
    private String matricula;

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    private Integer númeroPágina = 0;

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    private Integer tamanhoPágina = 10;
}