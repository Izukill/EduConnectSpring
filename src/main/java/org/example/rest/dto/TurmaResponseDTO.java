package org.example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class TurmaResponseDTO {


    private Long id;


    private UUID lookupId;


    private String nome;


    private String turno;
}