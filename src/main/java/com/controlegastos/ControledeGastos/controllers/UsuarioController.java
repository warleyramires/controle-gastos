package com.controlegastos.ControledeGastos.controllers;

import com.controlegastos.ControledeGastos.dtos.UsuarioDTO;
import com.controlegastos.ControledeGastos.entities.Despesa;
import com.controlegastos.ControledeGastos.entities.Usuario;
import com.controlegastos.ControledeGastos.services.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Gerenciar Usuários", description = "")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Operation(summary = "Criar um novo Usuario", description = "Add um novo usuário ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Request inválido",
                    content = @Content(schema = @Schema()))
    })

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        try{
            UsuarioDTO newUser = usuarioService.createUser(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Busca todos os usuários", description = "Retorna uma lista com todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    })
    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try{
            List<UsuarioDTO> usuarios = usuarioService.findAll();


            return ResponseEntity.status(HttpStatus.OK).body(usuarios);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna usuário pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{usuario_id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long usuario_id) {
        try{
            UsuarioDTO usuario = usuarioService.findById(usuario_id);

            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor indisponível",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{usuario_id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long usuario_id, @RequestBody Usuario usuario) {
        try{
            UsuarioDTO usuarioDTO = usuarioService.updateUsuario(usuario_id, usuario);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Deleta um usuário", description = "Deleta um usuário existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Servidor Indisponível",
                    content = @Content(schema = @Schema()))
    })
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
