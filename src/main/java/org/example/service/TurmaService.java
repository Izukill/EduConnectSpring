package org.example.service;

import org.example.exception.OperationException;
import org.example.model.Turma;
import org.example.repository.TurmaRepository;
import org.example.rest.dto.TurmaBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Transactional
    public Turma criar(Turma obj) throws OperationException {
        if (repository.existsByNome(obj.getNome())) {
            throw new OperationException("Já existe uma turma cadastrada com o nome: " + obj.getNome());
        }

        return repository.save(obj);
    }

    public List<Turma> recuperarTodos() {
        return repository.findAll();
    }

    // Busca exata pelo UUID usando Example (Padrão do seu projeto)
    public Optional<Turma> buscarPor(UUID lookupId) {
        Turma objExemplo = Turma.builder().lookupId(lookupId).build();
        Example<Turma> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Turma atualizar(Turma obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Turma obj) {
        repository.delete(obj);
    }

    public Page<Turma> buscar(TurmaBuscarDTO dto) {
        return repository.buscarPor(
                dto.getNome(),
                dto.getTurno(),
                PageRequest.of(dto.getPagina(), dto.getTamanho())
        );
    }
}