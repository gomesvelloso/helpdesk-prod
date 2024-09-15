package com.example.demo.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Chamado;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Tecnico;
import com.example.demo.domain.enuns.Perfil;
import com.example.demo.domain.enuns.Prioridade;
import com.example.demo.domain.enuns.Status;
import com.example.demo.repositories.ChamadoRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Dennis Henrique", "323.268.110-70", "testedennis@teste.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Bernard Mendes", "659.531.620-06", "testebernard@teste.com", encoder.encode("123"));
		tec2.addPerfil(Perfil.TECNICO);
		
		Tecnico tec3 = new Tecnico(null, "Saulo Carneiro", "925.291.100-69", "testesaulo@teste.com", encoder.encode("123"));
		tec3.addPerfil(Perfil.TECNICO);
		
		Tecnico tec4 = new Tecnico(null, "Bilé Palilo", "873.252.450-17", "testebile@teste.com", encoder.encode("123"));
		tec4.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Paula Ferreira", "012.345.678-90", "testepaula@hotmail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Micalateia Alice", "085.687.730-10", "testemicalateia@hotmail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Cleide Augênia", "522.707.650-28", "testecliede@hotmail.com", encoder.encode("123"));
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado cha2 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec2, cli2);
		Chamado cha3 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 03", "Terceiro chamado", tec3, cli3);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		chamadoRepository.saveAll(Arrays.asList(cha1, cha2, cha3));
	}
}
