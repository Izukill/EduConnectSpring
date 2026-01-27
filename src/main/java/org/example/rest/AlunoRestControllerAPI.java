package org.example.rest;

import java.util.List;
import java.util.UUID;

import org.example.exception.EduConnectException;
import org.example.rest.dto.AlunoBuscarDTO;
import org.example.rest.dto.AlunoResponseDTO;
import org.example.rest.dto.AlunoSalvarRequestDTO;
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

@Tag(name = "Alunos", description = "Gerenciameno de Alunos")
public interface AlunoRestControllerAPI {

    @Operation(summary = "Retornar todos os Alunos.",
            description = "Retorna todos os alunos, sem restrição alguma de quantidade.",
            tags = { "aluno" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlunoResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<AlunoResponseDTO>> listar() throws EduConnectException;

    @Operation(summary = "Criar um novo Aluno.",
            description = "Cria um novo Aluno com base na descrição informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> adicionar(@RequestBody(description = "Dados do aluno a ser criada.")
                                               AlunoSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Recuperar um aluno existente.",
            description = "Recupera um aluno existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Aluno com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> recuperarPor(@Parameter(description = "LookupId do aluno a ser recuperada.")
                                                  UUID lookupId) throws EduConnectException;

    @Operation(summary = "Atualizar um aluno existente.",
            description = "Atualiza um aluno existente com base no seu lookupId, permitindo atualização do seus dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Aluno com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> atualizar(@Parameter(description = "LookupId do aluno a ser atualizada.")
                                               UUID lookupId,
                                               @RequestBody(description = "Dados do aluno a ser atualizada.")
                                               AlunoSalvarRequestDTO dto) throws EduConnectException;

    @Operation(summary = "Remover um aluno existente.",
            description = "Remove um aluno existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Aluno com lookupId NÃO encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do aluno a ser removida.")
                                 UUID lookupId) throws EduConnectException;

    @Operation(summary = "Recuperar alunos existentes.",
            description = "Recupera alunos existentes de forma paginada com base nos seguintes filtros opcionais: (nome, matrícula, etc).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class, contentSchema = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<AlunoResponseDTO>> buscar(@ParameterObject AlunoBuscarDTO dto) throws EduConnectException;



}