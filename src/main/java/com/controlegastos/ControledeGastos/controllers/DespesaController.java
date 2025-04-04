package com.controlegastos.ControledeGastos.controllers;

import com.controlegastos.ControledeGastos.entities.Despesa;
import com.controlegastos.ControledeGastos.services.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    private final DespesaService despesaService;
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping("/users/{usuarioId}")
    public ResponseEntity<Despesa> createDespesa(@PathVariable Long usuarioId, @RequestBody Despesa despesa) {

        try{
            Despesa despesa1 = despesaService.createDespesa(usuarioId, despesa);

            return ResponseEntity.status(HttpStatus.CREATED).body(despesa1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Despesa>> getAllDespesas() {
        try{
            List<Despesa> despesas = despesaService.getDespesas();

            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{usuarioId}")
    public ResponseEntity<List<Despesa>> getAllDespesasByUsuario(@PathVariable Long usuarioId) {
        try{
            List<Despesa> listDespesa = despesaService.getDespesasByUsuario(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(listDespesa);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{despesaId}")
    public ResponseEntity<Void> deleteDespesa(@PathVariable Long despesaId) {
        try{
            despesaService.deleteDespesa(despesaId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
