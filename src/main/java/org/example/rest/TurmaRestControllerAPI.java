package org.example.rest;

import java.util.List;
import java.util.UUID;

import org.example.exception.EduConnectException;
import org.example.rest.dto.TurmaBuscarDTO;
import org.example.rest.dto.TurmaResponseDTO;
import org.example.rest.dto.TurmaSalvarRequestDTO;
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

@Tag(name = "Turmas", description = "Gerenciamento de Turmas")
public interface TurmaRestControllerAPI {

    @Operation(summary = "Retornar todas as Turmas.",
            description = "Retorna todas as turmas, sem restrição alguma de quantidade.",
            tags = { "turma" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TurmaResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<TurmaResponseDTO>> listar() throws EduConnectException;

    @Operation(summary = "Criar uma nova Turma.",
            description = "Cria uma nova Turma com base nos dados informados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurmaResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<TurmaResponseDTO> adicionar(@RequestBody(description = "Dados da turma a ser criada.")
                                               TurmaSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Recuperar uma turma existente.",
            description = "Recupera uma turma existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurmaResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Turma com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<TurmaResponseDTO> recuperarPor(@Parameter(description = "LookupId da turma a ser recuperada.")
                                                  UUID lookupId) throws EduConnectException;

    @Operation(summary = "Atualizar uma turma existente.",
            description = "Atualiza uma turma existente com base no seu lookupId, permitindo atualização dos seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurmaResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Turma com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<TurmaResponseDTO> atualizar(@Parameter(description = "LookupId da turma a ser atualizada.")
                                               UUID lookupId,
                                               @RequestBody(description = "Dados da turma a ser atualizada.")
                                               TurmaSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Remover uma turma existente.",
            description = "Remove uma turma existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Turma com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId da turma a ser removida.")
                                 UUID lookupId) throws EduConnectException;

    @Operation(summary = "Recuperar turmas existentes.",
            description = "Recupera turmas existentes de forma paginada com base nos seguintes filtros opcionais: (nome, turno).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class, contentSchema = TurmaResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<TurmaResponseDTO>> buscar(@ParameterObject TurmaBuscarDTO dto) throws EduConnectException;

}