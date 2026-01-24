package org.example.mapper;

import org.example.model.Aluno;
import org.example.rest.dto.AlunoResponseDTO;
import org.example.rest.dto.AlunoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    // 1. Converter DTO de Entrada para Entidade (Para Salvar)
    public Aluno from(AlunoSalvarRequestDTO dto) {
        return Aluno.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                // Nota: A senha aqui está indo pura. O ideal é criptografar no Service antes de salvar.
                .senha_hash(dto.getSenha())
                .matricula(dto.getMatricula())
                // OBS: Não setamos a 'Turma' aqui porque o Mapper não acessa o banco.
                // O Service vai pegar o 'dto.getTurmaId()', buscar a Turma e setar no aluno.
                .build();
    }

    // 2. Converter Entidade para DTO de Saída (Para o Front-end)
    public AlunoResponseDTO from(Aluno entity) {
        AlunoResponseDTO dto = new AlunoResponseDTO();

        // Dados de identificação
        dto.setLookupId(entity.getLookupId());

        // Dados Pessoais
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setMatricula(entity.getMatricula());

        // Tratamento seguro do Relacionamento Turma
        // Verificamos se é nulo para não dar erro (NullPointerException)
        if (entity.getTurma() != null) {
            dto.setNomeTurma(entity.getTurma().getNome());
            // Se tiver turno no DTO, adicione: dto.setTurno(entity.getTurma().getTurno());
        }

        return dto;
    }
}