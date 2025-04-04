package com.controlegastos.ControledeGastos.repostiories;

import com.controlegastos.ControledeGastos.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
