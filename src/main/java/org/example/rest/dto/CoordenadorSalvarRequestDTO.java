package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CoordenadorSalvarRequestDTO {

    @Schema(description = "Nome completo do coordenador", example = "Carlos Silva")
    @NotBlank
    private String nome;

    @Schema(description = "Email corporativo", example = "carlos@escola.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Senha de acesso", example = "123456")
    @NotBlank
    private String senha;

    @Schema(description = "CPF do coordenador", example = "123.456.789-00")
    @NotBlank
    private String cpf;

    @Schema(description = "Telefone de contato", example = "83 99999-9999")
    @NotBlank
    private String telefone;

    @Schema(description = "Salário mensal", example = "6500.00")
    @NotNull
    @Positive
    private Float salario;
}