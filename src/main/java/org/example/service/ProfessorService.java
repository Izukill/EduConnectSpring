package org.example.service;

import org.example.model.Professor;
import org.example.repository.ProfessorRepository;
import org.example.rest.dto.ProfessorBuscarDTO;
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
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Transactional
    public Professor criar(Professor obj) {

        obj.setSenha_hash("{noop}" + obj.getSenha_hash());
        return repository.save(obj);
    }

    public List<Professor> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Professor> buscarPor(UUID lookupId) {
        Professor objExemplo = Professor.builder().lookupId(lookupId).build();
        Example<Professor> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Professor atualizar(Professor obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Professor obj) {
        repository.delete(obj);
    }

    public Page<Professor> buscar(ProfessorBuscarDTO dto) {
        return repository.buscarPor(dto.getNome(), dto.getEmail(), dto.getSalarioMinimo(), PageRequest.of(dto.getPagina(), dto.getTamanho())
        );
    }
}