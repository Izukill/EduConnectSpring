package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProfessorSalvarRequestDTO {

    @Schema(description = "Nome completo do professor", example = "Ana Souza")
    @NotBlank
    private String nome;

    @Schema(description = "Email do professor", example = "ana.prof@escola.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Senha de acesso", example = "prof123")
    @NotBlank
    private String senha;

    @Schema(description = "CPF do professor", example = "111.222.333-44")
    @NotBlank
    private String cpf;

    @Schema(description = "Telefone de contato", example = "83 98888-8888")
    @NotBlank
    private String telefone;

    @Schema(description = "Salário mensal", example = "4500.00")
    @NotNull
    @Positive
    private Float salario;

}