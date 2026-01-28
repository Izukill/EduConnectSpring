package org.example.service;

import org.example.exception.EduConnectException;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.model.Aluno;
import org.example.model.Turma;
import org.example.repository.AlunoRepository;
import org.example.repository.TurmaRepository;
import org.example.rest.dto.AlunoBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional
    public Aluno criar(Aluno aluno, UUID turmaId) throws EduConnectException {

        aluno.setMatricula(""+ System.nanoTime());

        aluno.setSenha_hash(criptografarSenha(aluno.getSenha_hash()));


        if (turmaId != null) {
            Turma turmaExemplo = Turma.builder().lookupId(turmaId).build();



            Turma turmaEncontrada = turmaRepository.findOne(Example.of(turmaExemplo))
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma", turmaId));


            aluno.setTurma(turmaEncontrada);
        }

        return repository.save(aluno);
    }

    public List<Aluno> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Aluno> buscarPor(UUID lookupId) {
        Aluno objExemplo = Aluno.builder().lookupId(lookupId).build();
        Example<Aluno> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Aluno atualizar(Aluno obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Aluno obj) {
        repository.delete(obj);
    }

    public Page<Aluno> buscar(AlunoBuscarDTO dto) {

        return repository.buscarPor(dto.getNome(), dto.getMatricula(), PageRequest.of(dto.getNúmeroPágina(), dto.getTamanhoPágina()));
    }


    private String criptografarSenha(String senhaPura) {
        return System.currentTimeMillis() + senhaPura;
    }
}