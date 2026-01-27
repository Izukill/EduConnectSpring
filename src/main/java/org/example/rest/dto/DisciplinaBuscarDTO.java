package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class DisciplinaBuscarDTO {
    @Schema(description = "Filtra por professor.")
    private UUID idProfessor;

    @Schema(description = "Filtra por carga horária.")
    private int ch;
    @Schema(description = "Filtra por nome.")
    private String nome;

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    private Integer númeroPágina = 0;

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    private Integer tamanhoPágina = 10;
}
