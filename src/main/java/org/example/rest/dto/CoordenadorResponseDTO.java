package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class CoordenadorResponseDTO {


    private UUID lookupId;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Float salario;


}