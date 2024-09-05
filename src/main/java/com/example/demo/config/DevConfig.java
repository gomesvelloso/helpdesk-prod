package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBService;

//Porque é uma classe de configuração, colocamos a anotação configuration
@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean//Quando estiver com o profile dev ativo no application.properties o @Bean vai subir o método de forma automática
	public boolean intanciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false;
	}
	 
	
}