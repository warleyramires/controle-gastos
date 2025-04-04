package com.controlegastos.ControledeGastos.controllers;

import com.controlegastos.ControledeGastos.dtos.UsuarioDTO;
import com.controlegastos.ControledeGastos.entities.Despesa;
import com.controlegastos.ControledeGastos.entities.Usuario;
import com.controlegastos.ControledeGastos.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        try{
            UsuarioDTO newUser = usuarioService.createUser(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try{
            List<UsuarioDTO> usuarios = usuarioService.findAll();


            return ResponseEntity.status(HttpStatus.OK).body(usuarios);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{usuario_id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long usuario_id) {
        try{
            UsuarioDTO usuario = usuarioService.findById(usuario_id);

            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{usuario_id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long usuario_id, @RequestBody Usuario usuario) {
        try{
            UsuarioDTO usuarioDTO = usuarioService.updateUsuario(usuario_id, usuario);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{usuario_id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long usuario_id) {
        try{
            usuarioService.deleteUsuario(usuario_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
