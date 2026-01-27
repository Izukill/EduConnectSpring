package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PresencaSalvarRequestDTO {
    @Schema(description = "Status da presença", example = "Presente")
    @NotBlank
    private String status;

    @Schema(description = "Presença de aluno", example = "Arthur Santos")
    @NotBlank
    @Email
    private String nomeAluno;

    @Schema(description = "ID único da turma")
    @NotNull
    private UUID turmaId;
}
