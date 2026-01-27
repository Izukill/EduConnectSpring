package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TurmaBuscarDTO {
    @Schema(description = "Filtra pelo turno.")
    private String turno;

    @Schema(description = "Filtra pelo nome de aluno")
    private String nome;

    @Schema(description = "Filtra por aula")
    private String aula;

    @Schema(description = "Número da página (começa em 0). Padrão: 0")
    private Integer pagina = 0;

    @Schema(description = "Quantidade de itens por página. Padrão: 10")
    private Integer tamanho = 10;
}

