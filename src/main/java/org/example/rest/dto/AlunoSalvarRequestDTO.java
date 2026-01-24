package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AlunoSalvarRequestDTO {

    @Schema(description = "Nome completo do aluno", example = "Luan Loreto")
    @NotBlank
    private String nome;

    @Schema(description = "Email do aluno para login", example = "luanloreto@email.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Senha de acesso", example = "123456")
    @NotBlank
    private String senha;

    @Schema(description = "CPF do aluno", example = "111.222.333-44")
    @NotBlank
    private String cpf;

    @Schema(description = "Telefone do aluno", example = "83 99999-9999")
    @NotBlank
    private String telefone;

    @Schema(description = "ID único da turma onde o aluno será matriculado")
    @NotNull
    private UUID turmaId;
}