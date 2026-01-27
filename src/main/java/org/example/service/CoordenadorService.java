package org.example.service;

import org.example.model.Coordenador;
import org.example.repository.CoordenadorRepository;
import org.example.rest.dto.CoordenadorBuscarDTO;
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
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;

    @Transactional
    public Coordenador criar(Coordenador obj) {

        obj.setSenha_hash("{noop}" + obj.getSenha_hash());
        return repository.save(obj);
    }

    public List<Coordenador> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Coordenador> buscarPor(UUID lookupId) {
        Coordenador objExemplo = Coordenador.builder().lookupId(lookupId).build();
        Example<Coordenador> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Coordenador atualizar(Coordenador obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Coordenador obj) {
        repository.delete(obj);
    }

    public Page<Coordenador> buscar(CoordenadorBuscarDTO dto) {
        return repository.buscarPor(dto.getNome(), dto.getEmail(), dto.getSalarioMinimo(), PageRequest.of(dto.getPagina(), dto.getTamanho())
        );
    }
}