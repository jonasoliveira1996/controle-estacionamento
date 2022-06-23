package com.br.controle.estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.br.controle.estacionamento.repositorios")
@SpringBootApplication
public class ControleEstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleEstacionamentoApplication.class, args);
	}

}
