package org.example.rest.dto;

import lombok.Data;
import org.example.model.Aluno;
import org.example.model.Aula;
import org.example.model.Disciplina;

import java.util.List;
import java.util.UUID;

@Data
public class TurmaRespondeDTO {
    private UUID lookupId;
    private String turno;

}
