package org.example.rest;

import java.util.List;
import java.util.UUID;

import org.example.exception.EduConnectException;
import org.example.rest.dto.CoordenadorBuscarDTO;
import org.example.rest.dto.CoordenadorResponseDTO;
import org.example.rest.dto.CoordenadorSalvarRequestDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Coordenadores", description = "Gerenciamento de Coordenadores")
public interface CoordenadorRestControllerAPI {

    @Operation(summary = "Retornar todos os Coordenadores.",
            description = "Retorna todos os coordenadores, sem restrição alguma de quantidade.",
            tags = { "coordenador" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CoordenadorResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<CoordenadorResponseDTO>> listar() throws EduConnectException;

    @Operation(summary = "Criar um novo Coordenador.",
            description = "Cria um novo Coordenador com base nos dados informados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoordenadorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<CoordenadorResponseDTO> adicionar(@RequestBody(description = "Dados do coordenador a ser criado.")
                                                     CoordenadorSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Recuperar um coordenador existente.",
            description = "Recupera um coordenador existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoordenadorResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Coordenador com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<CoordenadorResponseDTO> recuperarPor(@Parameter(description = "LookupId do coordenador a ser recuperado.")
                                                        UUID lookupId) throws EduConnectException;

    @Operation(summary = "Atualizar um coordenador existente.",
            description = "Atualiza um coordenador existente com base no seu lookupId, permitindo atualização dos seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoordenadorResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Coordenador com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<CoordenadorResponseDTO> atualizar(@Parameter(description = "LookupId do coordenador a ser atualizado.")
                                                     UUID lookupId,
                                                     @RequestBody(description = "Dados do coordenador a ser atualizado.")
                                                     CoordenadorSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Remover um coordenador existente.",
            description = "Remove um coordenador existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Coordenador com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do coordenador a ser removido.")
                                 UUID lookupId) throws EduConnectException;

    @Operation(summary = "Recuperar coordenadores existentes.",
            description = "Recupera coordenadores existentes de forma paginada com base nos seguintes filtros opcionais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class, contentSchema = CoordenadorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<CoordenadorResponseDTO>> buscar(@ParameterObject CoordenadorBuscarDTO dto) throws EduConnectException;

}