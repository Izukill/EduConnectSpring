package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CoordenadorBuscarDTO {

    @Schema(description = "Filtra pelo nome do coordenador")
    private String nome;

    @Schema(description = "Filtra pelo email exato")
    private String email;

    @Schema(description = "Filtra pela faixa de salário mínima")
    private Float salarioMinimo;

    @Schema(description = "Número da página (começa em 0). Padrão: 0")
    private Integer pagina = 0;

    @Schema(description = "Quantidade de itens por página. Padrão: 10")
    private Integer tamanho = 10;
}