package com.controlegastos.ControledeGastos.controllers;

import com.controlegastos.ControledeGastos.dtos.UsuarioDTO;
import com.controlegastos.ControledeGastos.entities.Despesa;
import com.controlegastos.ControledeGastos.services.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/despesas")
@Tag(name = "Gerenciar Despesas", description= "")
public class DespesaController {

    private final DespesaService despesaService;
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @Operation(summary = "Criar uma despesa", description = "Add uma nova despesa ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa criada com sucesso",
                    content = @Content(schema = @Schema(implementation = Despesa.class))),
            @ApiResponse(responseCode = "400", description = "Request inválido",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/users/{usuarioId}")
    public ResponseEntity<Despesa> createDespesa(@PathVariable Long usuarioId, @RequestBody Despesa despesa) {

        try{
            Despesa despesa1 = despesaService.createDespesa(usuarioId, despesa);

            return ResponseEntity.status(HttpStatus.CREATED).body(despesa1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Busca todas as despesas", description = "Retorna uma lista com todas as despesas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesas retornadas com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))

    })
    @GetMapping
    public ResponseEntity<List<Despesa>> getAllDespesas() {
        try{
            List<Despesa> despesas = despesaService.getDespesas();

            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary = "Busca uma Despesa pelo ID do Usuario", description = "Retorna uma Despesa pelo ID do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa found",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Despesa not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/users/{usuarioId}")
    public ResponseEntity<List<Despesa>> getAllDespesasByUsuario(@PathVariable Long usuarioId) {
        try{
            List<Despesa> listDespesa = despesaService.getDespesasByUsuario(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(listDespesa);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Deleta uma Despesa", description = "Deleta uma Despesa existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Despesa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor Indisponível",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{despesaId}")
    public ResponseEntity<Void> deleteDespesa(@PathVariable Long despesaId) {
        try{
            despesaService.deleteDespesa(despesaId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Atualiza uma Despesa", description = "Atualiza uma despesa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Despesa atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Despesa não encontrada",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{despesaId}")
    public ResponseEntity<Despesa> updateDespesa(@PathVariable Long despesaId, @RequestBody Despesa despesa) {
        try{
            Despesa despesa1 = despesaService.updateDespesa(despesaId, despesa);
            return ResponseEntity.status(HttpStatus.OK).body(despesa1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
