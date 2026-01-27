package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TurmaSalvarRequestDTO {

    @Schema(description = "Nome da turma", example = "Turma 1-A")
    @NotBlank(message = "O nome da turma é obrigatório")
    private String nome;

    @Schema(description = "Turno da turma", example = "Manha")
    @NotBlank(message = "O turno é obrigatório")
    private String turno;
}