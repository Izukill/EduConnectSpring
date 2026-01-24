package org.example.rest.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AlunoResponseDTO {


    private UUID lookupId;

    private String nome;

    private String email;

    private String matricula;

    private String nomeTurma;

}