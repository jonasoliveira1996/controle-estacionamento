package com.br.controle.estacionamento.controlador;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.controle.estacionamento.modelo.Estacionamento;
import com.br.controle.estacionamento.repositorios.EstacionamentoRepositorio;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoControlador {
		
		@Autowired
		private EstacionamentoRepositorio estacionamentoRepositorio;

		@GetMapping
		public ResponseEntity<List<Estacionamento>> obterTodosRegistros() {
			return ResponseEntity.status(HttpStatus.OK).body(estacionamentoRepositorio.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Object> obterRegistrosPorId(@PathVariable Long id) {
			
			Optional<Estacionamento> estacionamentoOptional = estacionamentoRepositorio.findById(id);
			if(!estacionamentoOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada.");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(estacionamentoOptional.get());
		}
		
		@PostMapping
		public ResponseEntity<Object> inserirRegistro(@RequestBody @Valid Estacionamento estacionamento) {
			
			estacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
						
			if(estacionamentoRepositorio.existsByPlacaCarro(estacionamento.getPlacaCarro())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa do carro já existe.");
			}
			
			if(estacionamentoRepositorio.existsBynumeroVagaEstacionamento(estacionamento.getNumeroVagaEstacionamento())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de estacionamento já está em uso.");
			}
			
			if(estacionamentoRepositorio.existsByApartamentoAndBloco(estacionamento.getApartamento(), estacionamento.getBloco())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Apartamento/bloco já está em uso.");
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoRepositorio.save(estacionamento));
		}
		
		@PutMapping
		public ResponseEntity<Object> alterarRegistro(@RequestBody @Valid Estacionamento estacionamento){
			
			Optional<Estacionamento> estacionamentoOptional = estacionamentoRepositorio.findById(estacionamento.getId());
			
			if(!estacionamentoOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrada");
			}
			
			estacionamentoRepositorio.save(estacionamento);
			return ResponseEntity.status(HttpStatus.OK).body("Registro alterado com sucesso.");
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Object> excluirRegistro(@PathVariable Long id){
			
			Optional<Estacionamento> estacionamentoOptional = estacionamentoRepositorio.findById(id);
			
			if(!estacionamentoOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encotrada.");
			}
			
			estacionamentoRepositorio.delete(estacionamentoOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("Registro removido com sucesso.");
		}
		
}
