package org.example.mapper;

import org.example.model.Aluno;
import org.example.rest.dto.AlunoResponseDTO;
import org.example.rest.dto.AlunoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    //converte para salvar
    public Aluno from(AlunoSalvarRequestDTO dto) {
        return Aluno.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .senha_hash(dto.getSenha())// a senha será quiptografada no service
                //matricula será gerada automáticamente no service
                .build();
    }


    public AlunoResponseDTO from(Aluno entity) {
        AlunoResponseDTO dto = new AlunoResponseDTO();


        dto.setLookupId(entity.getLookupId());

        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setMatricula(entity.getMatricula());

        if (entity.getTurma() != null) {
            dto.setNomeTurma(entity.getTurma().getNome());
        }

        return dto;
    }
}