package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AulaSalvarRequestDTO {
    @Schema(description = "Conteúdo da aula", example = "DAW II")
    @NotBlank
    private String conteudo;

    @Schema(description = "Data da aula", example = "28/01/2026")
    @NotBlank
    private String email;

    @Schema(description = "ID único da turma")
    @NotNull
    private UUID turmaId;
}
