package org.example.rest;

import java.util.List;
import java.util.UUID;

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
public interface TodoRestControllerApi {

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
    ResponseEntity<List<AlunoResponseDTO>> listar() throws TodoException;

    @Operation(summary = "Criar uma nova tarefa.",
            description = "Cria uma nova tarefa com base na descrição informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> adicionar(@RequestBody(description = "Dados da tarefa a ser criada.")
                                              AlunoSalvarRequestDTO dto) throws TodoException;

    @Operation(summary = "Recuperar uma tarefa existente.",
            description = "Recupera uma tarefa existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Tarefa com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> recuperarPor(@Parameter(description = "LookupId da tarefa a ser recuperada.")
                                                 UUID lookupId) throws TodoException;

    @Operation(summary = "Atualizar uma tarefa existente.",
            description = "Atualiza uma tarefa existente com base no seu lookupId, permitindo atualização da descrição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Tarefa com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> atualizar(@Parameter(description = "LookupId da tarefa a ser atualizada.")
                                              UUID lookupId,
                                              @RequestBody(description = "Dados da tarefa a ser atualizada.")
                                               AlunoSalvarRequestDTO dto) throws TodoException;

    @Operation(summary = "Remover uma tarefa existente.",
            description = "Remove uma tarefa existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Tarefa com lookupId NÃO encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId da tarefa a ser removida.")
                                 UUID lookupId) throws TodoException;

    @Operation(summary = "Recuperar tarefas existentes.",
            description = "Recupera tarefas existentes de forma paginada com base nos seguintes filtros opcionais: situação de conclusão (sim ou não) e descrição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            /*array = @ArraySchema(schema = @Schema(implementation = TodoResponseDTO.class))*/
                            schema = @Schema(implementation = Page.class, contentSchema = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<AlunoResponseDTO>> buscar(@ParameterObject TodoBuscarDTO dto) throws TodoException;

    @Operation(summary = "Marcar uma tarefa como concluída.",
            description = "Marca uma tarefa como concluída, lançando erro caso ela já esteja concluída.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Tarefa com lookupId NÃO encontrada ou tarefa já foi concluída.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> fazerTarefa(@Parameter(description = "LookupId da tarefa a ser marcada como concluída.")
                                                UUID lookupId) throws TodoException;

    @Operation(summary = "Desfazer uma tarefa que foi concluída.",
            description = "Desfaz uma tarefa que foi concluída, lançando erro caso ela não tenha sido concluída ainda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Tarefa com lookupId NÃO encontrada ou tarefa NÃO está marcada como concluída.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AlunoResponseDTO> desfazerTarefa(@Parameter(description = "LookupId da tarefa a ser desfeita.")
                                                   UUID lookupId) throws TodoException;

}