package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Chamado;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Tecnico;
import com.example.demo.domain.dtos.ChamadoDTO;
import com.example.demo.domain.enuns.Prioridade;
import com.example.demo.domain.enuns.Status;
import com.example.demo.repositories.ChamadoRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;


@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Autowired 
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado findById(Integer id)  {
		System.out.println("id: "+id);
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
	}
	
	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	public Chamado create(ChamadoDTO objDTO) {
		return repository.save(
				newChamado(objDTO));
	}
	
	public Chamado update(Integer id, ChamadoDTO objDTO) {
		objDTO.setId(id);
		
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		
		return repository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			
			chamado.setId(obj.getId());
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
	}
}
