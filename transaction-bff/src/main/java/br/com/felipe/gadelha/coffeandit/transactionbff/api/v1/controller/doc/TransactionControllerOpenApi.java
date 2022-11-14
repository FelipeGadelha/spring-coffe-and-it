package br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.controller.doc;

import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.request.TransactionRq;
import br.com.felipe.gadelha.coffeandit.transactionbff.api.v1.dto.response.TransactionRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "Transactions",
    description = "Grupo de endpoint's para manupulação de transações financeiras")
public interface TransactionControllerOpenApi {

    @Operation(summary = "Buscar uma transação financeira por ID", responses = {
        @ApiResponse(responseCode = "200", description = "Transação financeira encontrada"),
        @ApiResponse(responseCode = "404", description = "Transação financeira não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", description = "ID fornecido inválido",
            content = @Content(schema = @Schema(hidden = true)))
    })
    Mono<TransactionRs> findById(
        @Parameter(description = "ID de uma transação financeira", example = "1", required = true) String id);

    @Operation(summary = "Criar uma transação financeira", responses = {
        @ApiResponse(responseCode = "201", description = "Transação financeira criada"),
        @ApiResponse(responseCode = "404", description = "Transação financeira não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "403", description = "Erro de autorização",
            content = @Content(schema = @Schema(hidden = true))),
    })
    Mono<TransactionRs> sendTransaction(TransactionRq transactionRq);
    @Operation(summary = "Deletar transação financeira por ID", responses = {
        @ApiResponse(responseCode = "204", description = "Transação financeira deletada"),
        @ApiResponse(responseCode = "404", description = "Transação financeira não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", description = "ID fornecido inválido",
            content = @Content(schema = @Schema(hidden = true)))
    })
    Mono<TransactionRs> deleteById(
        @Parameter(description = "ID de uma transação financeira", example = "1", required = true) String id);
    @Operation(summary = "Autorizar transação financeira por ID", responses = {
        @ApiResponse(responseCode = "200", description = "Transação financeira autorizada"),
        @ApiResponse(responseCode = "404", description = "Transação financeira não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "400", description = "ID fornecido inválido",
            content = @Content(schema = @Schema(hidden = true)))
    })
    Mono<TransactionRs> authorize(
        @Parameter(description = "ID de uma transação financeira", example = "1", required = true) String id);
}
