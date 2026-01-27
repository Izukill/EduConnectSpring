package org.example.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.exception.EduConnectException;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.mapper.AlunoMapper;
import org.example.model.Aluno;
import org.example.rest.dto.AlunoBuscarDTO;
import org.example.rest.dto.AlunoResponseDTO;
import org.example.rest.dto.AlunoSalvarRequestDTO;
import org.example.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
public class AlunoRestController implements AlunoRestControllerAPI {

    @Autowired
    private AlunoMapper mapper;

    @Autowired
    private AlunoService service;

    @Override
    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listar() throws EduConnectException {
        List<Aluno> objs = service.recuperarTodos();
        List<AlunoResponseDTO> resultado = objs.stream()
                .map(mapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> adicionar(@RequestBody @Valid AlunoSalvarRequestDTO dto) throws EduConnectException {
        Aluno objNovo = mapper.from(dto);
        Aluno objCriado = service.criar(objNovo, dto.getTurmaId());

        AlunoResponseDTO resultado = mapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<AlunoResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws EduConnectException {
        Aluno obj = validarExiste(lookupId);
        AlunoResponseDTO resultado = mapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{lookupId}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid AlunoSalvarRequestDTO dto) throws EduConnectException {
        Aluno objExistente = validarExiste(lookupId);

        // Atualizando os campos permitidos
        objExistente.setNome(dto.getNome());
        objExistente.setEmail(dto.getEmail());
        objExistente.setTelefone(dto.getTelefone());
        objExistente.setCpf(dto.getCpf());

        Aluno objAtualizado = service.atualizar(objExistente);
        AlunoResponseDTO resultado = mapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws EduConnectException {
        Aluno obj = validarExiste(lookupId);
        service.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<AlunoResponseDTO>> buscar(AlunoBuscarDTO dto) throws EduConnectException {
        Page<Aluno> objs = service.buscar(dto);
        Page<AlunoResponseDTO> resultado = objs.map(mapper::from);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // Método auxiliar privado (Padrão do Professor)
    private Aluno validarExiste(UUID lookupId) throws EntidadeNaoEncontradaException {
        Optional<Aluno> opt = service.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Aluno", lookupId);
        }
        return opt.get();
    }
}