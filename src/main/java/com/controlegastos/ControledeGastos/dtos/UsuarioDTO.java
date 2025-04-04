package com.controlegastos.ControledeGastos.dtos;

import lombok.Getter;


public record UsuarioDTO(

        Long id,
        String nome,
        String email
) {
}
