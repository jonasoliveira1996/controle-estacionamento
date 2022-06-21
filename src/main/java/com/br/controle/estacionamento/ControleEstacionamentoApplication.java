package com.br.controle.estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ControleEstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleEstacionamentoApplication.class, args);
	}

}
