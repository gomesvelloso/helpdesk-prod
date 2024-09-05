package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBService;


//Porque é uma classe de configuração, colocamos a anotação configuration
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	
	@Bean//Quando estiver com o profile test ativo no application.properties o @Bean vai subir o método de forma automática
	public void intanciaDB() {
		this.dbService.instanciaDB();
	}
	 
	
}