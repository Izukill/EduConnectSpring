package org.example.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.exception.EduConnectException;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.mapper.TurmaMapper;
import org.example.model.Turma;
import org.example.rest.dto.TurmaBuscarDTO;
import org.example.rest.dto.TurmaResponseDTO;
import org.example.rest.dto.TurmaSalvarRequestDTO;
import org.example.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turmas")
public class TurmaRestController implements TurmaRestControllerAPI {

    @Autowired
    private TurmaMapper mapper;

    @Autowired
    private TurmaService service;

    @Override
    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listar() throws EduConnectException {
        List<Turma> objs = service.recuperarTodos();
        List<TurmaResponseDTO> resultado = objs.stream()
                .map(mapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<TurmaResponseDTO> adicionar(@RequestBody @Valid TurmaSalvarRequestDTO dto) throws EduConnectException {
        Turma objNovo = mapper.from(dto);
        Turma objCriado = service.criar(objNovo);
        TurmaResponseDTO resultado = mapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<TurmaResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws EduConnectException {
        Turma obj = validarExiste(lookupId);
        TurmaResponseDTO resultado = mapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{lookupId}")
    public ResponseEntity<TurmaResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid TurmaSalvarRequestDTO dto) throws EduConnectException {
        Turma objExistente = validarExiste(lookupId);

        // Atualização dos campos específicos da Turma
        objExistente.setNome(dto.getNome());
        objExistente.setTurno(dto.getTurno());

        Turma objAtualizado = service.atualizar(objExistente);
        TurmaResponseDTO resultado = mapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws EduConnectException {
        Turma obj = validarExiste(lookupId);
        service.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<TurmaResponseDTO>> buscar(TurmaBuscarDTO dto) throws EduConnectException {
        Page<Turma> objs = service.buscar(dto);
        Page<TurmaResponseDTO> resultado = objs.map(mapper::from);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Turma validarExiste(UUID lookupId) throws EntidadeNaoEncontradaException {
        Optional<Turma> opt = service.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Turma", lookupId);
        }
        return opt.get();
    }
}