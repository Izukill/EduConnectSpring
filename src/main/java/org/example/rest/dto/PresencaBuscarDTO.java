package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PresencaBuscarDTO {
    @Schema(description = "Filtra pelo status")
    private String statusPresenca;

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    private Integer númeroPágina = 0;

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    private Integer tamanhoPágina = 10;
}
