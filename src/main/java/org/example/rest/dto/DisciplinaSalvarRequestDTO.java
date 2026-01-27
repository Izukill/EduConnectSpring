package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class DisciplinaSalvarRequestDTO {
    @Schema(description = "Nome da disciplina", example = "DAW II")
    @NotBlank
    private String nome;

    @Schema(description = "Carga Horária", example = "67 h")
    @NotBlank
    private int ch;

    @Schema(description = "ementa", example = "padrão MVC, POO na web...")
    @NotBlank
    private String ementa;


    @Schema(description = "ID único da turma onde o aluno será matriculado")
    @NotNull
    private UUID turmaId;
}
