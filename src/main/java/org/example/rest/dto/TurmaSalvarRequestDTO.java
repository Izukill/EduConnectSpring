package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TurmaSalvarRequestDTO {
    @Schema(description = "Turno da turma", example = "Tarde")
    @NotBlank
    private String turno;

}
