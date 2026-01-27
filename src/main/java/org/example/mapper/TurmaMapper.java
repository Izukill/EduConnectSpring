package org.example.mapper;

import org.example.model.Turma;
import org.example.rest.dto.TurmaResponseDTO;
import org.example.rest.dto.TurmaSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {


    public Turma from(TurmaSalvarRequestDTO dto) {
        return Turma.builder()
                .nome(dto.getNome())
                .turno(dto.getTurno())
                .build();
    }


    public TurmaResponseDTO from(Turma entity) {
        TurmaResponseDTO dto = new TurmaResponseDTO();

        dto.setLookupId(entity.getLookupId());

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setTurno(entity.getTurno());


        return dto;
    }
}