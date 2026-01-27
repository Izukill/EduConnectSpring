package org.example.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.exception.EduConnectException;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.mapper.ProfessorMapper;
import org.example.model.Professor;
import org.example.rest.dto.ProfessorBuscarDTO;
import org.example.rest.dto.ProfessorResponseDTO;
import org.example.rest.dto.ProfessorSalvarRequestDTO;
import org.example.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professores")
public class ProfessorRestController implements ProfessorRestControllerAPI {

    @Autowired
    private ProfessorMapper mapper;

    @Autowired
    private ProfessorService service;

    @Override
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listar() throws EduConnectException {
        List<Professor> objs = service.recuperarTodos();
        List<ProfessorResponseDTO> resultado = objs.stream()
                .map(mapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> adicionar(@RequestBody @Valid ProfessorSalvarRequestDTO dto) throws EduConnectException {
        Professor objNovo = mapper.from(dto);
        Professor objCriado = service.criar(objNovo);
        ProfessorResponseDTO resultado = mapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<ProfessorResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws EduConnectException {
        Professor obj = validarExiste(lookupId);
        ProfessorResponseDTO resultado = mapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{lookupId}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ProfessorSalvarRequestDTO dto) throws EduConnectException {
        Professor objExistente = validarExiste(lookupId);

        //atualização dos campos
        objExistente.setNome(dto.getNome());
        objExistente.setEmail(dto.getEmail());
        objExistente.setTelefone(dto.getTelefone());
        objExistente.setSalario(dto.getSalario());
        objExistente.setCpf(dto.getCpf());

        Professor objAtualizado = service.atualizar(objExistente);
        ProfessorResponseDTO resultado = mapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws EduConnectException {
        Professor obj = validarExiste(lookupId);
        service.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<ProfessorResponseDTO>> buscar(ProfessorBuscarDTO dto) throws EduConnectException {
        Page<Professor> objs = service.buscar(dto);
        Page<ProfessorResponseDTO> resultado = objs.map(mapper::from);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Professor validarExiste(UUID lookupId) throws EntidadeNaoEncontradaException {
        Optional<Professor> opt = service.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Professor", lookupId);
        }
        return opt.get();
    }
}