package org.example.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import org.example.exception.EduConnectException;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.mapper.CoordenadorMapper;
import org.example.model.Coordenador;
import org.example.rest.dto.CoordenadorBuscarDTO;
import org.example.rest.dto.CoordenadorResponseDTO;
import org.example.rest.dto.CoordenadorSalvarRequestDTO;
import org.example.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorRestController implements CoordenadorRestControllerAPI {

    @Autowired
    private CoordenadorMapper mapper;

    @Autowired
    private CoordenadorService service;

    @Override
    @GetMapping
    public ResponseEntity<List<CoordenadorResponseDTO>> listar() throws EduConnectException {
        List<Coordenador> objs = service.recuperarTodos();
        List<CoordenadorResponseDTO> resultado = objs.stream()
                .map(mapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<CoordenadorResponseDTO> adicionar(@RequestBody @Valid CoordenadorSalvarRequestDTO dto) throws EduConnectException {
        Coordenador objNovo = mapper.from(dto);
        Coordenador objCriado = service.criar(objNovo);
        CoordenadorResponseDTO resultado = mapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<CoordenadorResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws EduConnectException {
        Coordenador obj = validarExiste(lookupId);
        CoordenadorResponseDTO resultado = mapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{lookupId}")
    public ResponseEntity<CoordenadorResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid CoordenadorSalvarRequestDTO dto) throws EduConnectException {
        Coordenador objExistente = validarExiste(lookupId);

        objExistente.setNome(dto.getNome());
        objExistente.setEmail(dto.getEmail());
        objExistente.setTelefone(dto.getTelefone());
        objExistente.setSalario(dto.getSalario());
        objExistente.setCpf(dto.getCpf());

        Coordenador objAtualizado = service.atualizar(objExistente);
        CoordenadorResponseDTO resultado = mapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws EduConnectException {
        Coordenador obj = validarExiste(lookupId);
        service.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<CoordenadorResponseDTO>> buscar(CoordenadorBuscarDTO dto) throws EduConnectException {
        Page<Coordenador> objs = service.buscar(dto);
        Page<CoordenadorResponseDTO> resultado = objs.map(mapper::from);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Coordenador validarExiste(UUID lookupId) throws EntidadeNaoEncontradaException {
        Optional<Coordenador> opt = service.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Coordenador", lookupId);
        }
        return opt.get();
    }
}