package com.controlegastos.ControledeGastos.services;

import com.controlegastos.ControledeGastos.entities.Despesa;
import com.controlegastos.ControledeGastos.entities.Usuario;
import com.controlegastos.ControledeGastos.repostiories.DespesaRepository;
import com.controlegastos.ControledeGastos.repostiories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final UsuarioRepository usuarioRepository;

    public DespesaService(DespesaRepository despesaRepository, UsuarioRepository usuarioRepository) {
        this.despesaRepository = despesaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Despesa createDespesa(Long usuario_id, Despesa despesa) {
        try {
            Usuario usuario = usuarioRepository.findById(usuario_id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Despesa newDespesa = new Despesa();
            newDespesa.setDescricao(despesa.getDescricao());
            newDespesa.setCategoria(despesa.getCategoria());
            newDespesa.setValor(despesa.getValor());

            if (despesa.getDataDespesa() != null) {
                newDespesa.setDataDespesa(despesa.getDataDespesa());
            } else {
                newDespesa.setDataDespesa(LocalDate.now()); // Usa a data atual
            }

            newDespesa.setUsuario(usuario);
            return despesaRepository.save(newDespesa);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criar despesa" + e.getMessage());
        }
    }

    public List<Despesa> getDespesas() {
        try{
            Optional<List<Despesa>> optionalDespesas = Optional.of(despesaRepository.findAll());

            if(optionalDespesas.isEmpty()){
                return optionalDespesas.get();
            }
            List<Despesa> despesas = optionalDespesas.get();

            return despesas;

        }catch (Exception e){
            throw new RuntimeException("Erro ao carregar despesas" + e.getMessage());
        }
    }

    public List<Despesa> getDespesasByUsuario(Long usuarioId) {
        try{
           Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

           if(optionalUsuario.isEmpty()){
               throw new RuntimeException("Usuário Inexistente");
           }
           return despesaRepository.findByUsuarioId(usuarioId);
        }catch (Exception e){
            throw new RuntimeException("Erro ao carregar despesas" + e.getMessage());
        }
    }

    public void deleteDespesa(Long despesaId) {
        try{
           Optional<Despesa> optionalDespesa = despesaRepository.findById(despesaId);

           if(optionalDespesa.isEmpty()){
               throw new RuntimeException("Despesa Inexistente");
           }

           despesaRepository.delete(optionalDespesa.get());

        }catch (Exception e){
            throw new RuntimeException("Erro ao deletar despesa" + e.getMessage());
        }
    }

    public Despesa updateDespesa(Long despesaId, Despesa despesa) {
        try{
            Optional<Despesa> optionalDespesa = despesaRepository.findById(despesaId);

            if(optionalDespesa.isEmpty()){
                throw new RuntimeException("Despesa Inexistente");
            }

            Despesa updatedDespesa = optionalDespesa.get();

            updatedDespesa.setDescricao(despesa.getDescricao());
            updatedDespesa.setCategoria(despesa.getCategoria());
            updatedDespesa.setValor(despesa.getValor());
            if (despesa.getDataDespesa() != null) {
                updatedDespesa.setDataDespesa(despesa.getDataDespesa());
            }

            return despesaRepository.save(updatedDespesa);
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar despesa" + e.getMessage());
        }
    }
}
