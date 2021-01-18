package com.osworks.works.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.osworks.works.domain.model.Cliente;
import com.osworks.works.domain.repository.ClienteRepository;

//Classe de regras de negócio
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
