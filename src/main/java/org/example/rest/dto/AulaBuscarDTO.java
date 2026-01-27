package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AulaBuscarDTO {
    @Schema(description = "Filtrar por conteúdo.")
    private String conteudo;

    @Schema(description = "Filtra por data.")
    private String data;

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    private Integer númeroPágina = 0;

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    private Integer tamanhoPágina = 10;
}
