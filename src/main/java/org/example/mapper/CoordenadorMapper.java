package org.example.mapper;

import org.example.model.Coordenador;
import org.example.rest.dto.CoordenadorResponseDTO;
import org.example.rest.dto.CoordenadorSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorMapper {

    //converte para salvar
    public Coordenador from(CoordenadorSalvarRequestDTO dto) {
        return Coordenador.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .senha_hash(dto.getSenha())
                .salario(dto.getSalario())
                .build();
    }


    public CoordenadorResponseDTO from(Coordenador entity) {
        CoordenadorResponseDTO dto = new CoordenadorResponseDTO();

        dto.setLookupId(entity.getLookupId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setCpf(entity.getCpf());
        dto.setSalario(entity.getSalario());

        return dto;
    }
}