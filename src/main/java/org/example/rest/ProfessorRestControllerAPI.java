package org.example.rest;

import java.util.List;
import java.util.UUID;

import org.example.exception.EduConnectException;
import org.example.rest.dto.ProfessorBuscarDTO;
import org.example.rest.dto.ProfessorResponseDTO;
import org.example.rest.dto.ProfessorSalvarRequestDTO;
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

@Tag(name = "Professores", description = "Gerenciamento de Professores")
public interface ProfessorRestControllerAPI {

    @Operation(summary = "Retornar todos os Professores.",
            description = "Retorna todos os professores, sem restrição alguma de quantidade.",
            tags = { "professor" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProfessorResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<ProfessorResponseDTO>> listar() throws EduConnectException;

    @Operation(summary = "Criar um novo Professor.",
            description = "Cria um novo Professor com base nos dados informados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProfessorResponseDTO> adicionar(@RequestBody(description = "Dados do professor a ser criado.")
                                                   ProfessorSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Recuperar um professor existente.",
            description = "Recupera um professor existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Professor com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProfessorResponseDTO> recuperarPor(@Parameter(description = "LookupId do professor a ser recuperado.")
                                                      UUID lookupId) throws EduConnectException;

    @Operation(summary = "Atualizar um professor existente.",
            description = "Atualiza um professor existente com base no seu lookupId, permitindo atualização dos seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Professor com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProfessorResponseDTO> atualizar(@Parameter(description = "LookupId do professor a ser atualizado.")
                                                   UUID lookupId,
                                                   @RequestBody(description = "Dados do professor a ser atualizado.")
                                                   ProfessorSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Remover um professor existente.",
            description = "Remove um professor existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Professor com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do professor a ser removido.")
                                 UUID lookupId) throws EduConnectException;

    @Operation(summary = "Recuperar professores existentes.",
            description = "Recupera professores existentes de forma paginada com base nos seguintes filtros opcionais: (nome, salário, etc).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class, contentSchema = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<ProfessorResponseDTO>> buscar(@ParameterObject ProfessorBuscarDTO dto) throws EduConnectException;

}