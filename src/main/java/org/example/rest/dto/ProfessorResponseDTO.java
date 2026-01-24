package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class ProfessorResponseDTO {

    private UUID lookupId;
    private String nome;
    private String email;
    private String telefone;
    private Float salario;

    // Opcional: Campo calculado apenas para visualização
    @Schema(description = "Quantidade de aulas que este professor ministra")
    private Integer totalAulas;
}