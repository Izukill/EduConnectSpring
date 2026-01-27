package org.example.mapper;

import org.example.model.Professor;
import org.example.rest.dto.ProfessorResponseDTO;
import org.example.rest.dto.ProfessorSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    //converte para salvar
    public Professor from(ProfessorSalvarRequestDTO dto) {
        return Professor.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .senha_hash(dto.getSenha())
                .salario(dto.getSalario())
                .build();
    }


    public ProfessorResponseDTO from(Professor entity) {
        ProfessorResponseDTO dto = new ProfessorResponseDTO();

        dto.setLookupId(entity.getLookupId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setSalario(entity.getSalario());

        return dto;
    }
}