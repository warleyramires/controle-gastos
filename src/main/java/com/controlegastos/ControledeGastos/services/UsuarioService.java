package com.controlegastos.ControledeGastos.services;

import com.controlegastos.ControledeGastos.dtos.UsuarioDTO;
import com.controlegastos.ControledeGastos.entities.Usuario;
import com.controlegastos.ControledeGastos.exceptions.UsuarioExistenteException;
import com.controlegastos.ControledeGastos.repostiories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public UsuarioDTO createUser(Usuario usuario) {
        try {

            Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

            if (usuarioExistente.isEmpty()) {
                Usuario usuario1 = new Usuario();
                usuario1.setEmail(usuario.getEmail());
                usuario1.setNome(usuario.getNome());
                usuario1.setSenha(usuario.getSenha());

                usuario1 = usuarioRepository.save(usuario1);
                return new UsuarioDTO(usuario1.getId(), usuario1.getNome(), usuario1.getEmail());
            }
            throw new UsuarioExistenteException("Usuario já cadastrado");


        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar usuario. " + e.getMessage());
        }
    }

    public List<UsuarioDTO> findAll() {
        try {
            Collection<Usuario> usuarios = usuarioRepository.findAll();
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();

            usuarios.forEach(usuario -> {
                usuariosDTO.add(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
            });
            return usuariosDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao listar usuario. " + e.getMessage());
        }
    }

    public UsuarioDTO findById(Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);

            if (usuario.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado");
            }
            return new UsuarioDTO(usuario.get().getId(), usuario.get().getNome(), usuario.get().getEmail());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar usuario. " + e.getMessage());
        }
    }

    public UsuarioDTO updateUsuario(Long id, Usuario usuario) {
        try {
            Usuario usuarioExistente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não existente"));

            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());

            Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);

            return new UsuarioDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuario. " + e.getMessage());
        }
    }

    public void deleteUsuario(Long id) {
        try{
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado");
            }
            usuarioRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao deletar usuario. " + e.getMessage());
        }
    }
}

