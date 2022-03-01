package com.br.controle.estacionamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.controle.estacionamento.modelo.Estacionamento;

@Repository
public interface EstacionamentoRepositorio extends JpaRepository<Estacionamento, Long>{
	
		public boolean existsByPlacaCarro(String placaCarro);
		public boolean existsBynumeroVagaEstacionamento(String numeroVagaEstacionamento);
		public boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}
