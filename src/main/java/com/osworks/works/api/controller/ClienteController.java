package com.osworks.works.api.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.osworks.works.domain.model.Cliente;
import com.osworks.works.domain.repository.ClienteRepository;
import com.osworks.works.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	//@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
		
	}
	//Metodo de Busca do cliente no banco
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente>buscar(@PathVariable Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		//Condicao que cria resposta se tem cliente na solicitacao
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		//Caso contrario retorna notFOund 404
		return ResponseEntity.notFound().build();
		
	}
	/*Metodo de cadastro do cliente
	 * @Valid ativa a validação do campos */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		
		return cadastroCliente.salvar(cliente);
	}
	
	//Lógica com metodo para atualização do cadastro de clientes
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
				@RequestBody Cliente cliente){
				if(!clienteRepository.existsById(clienteId)) {
				return ResponseEntity.notFound().build();	
				}
				
				cliente.setId(clienteId);
				cliente = cadastroCliente.salvar(cliente);
				
				return ResponseEntity.ok(cliente);
				
				}
	
	//Lógica com metodo para Excluzão do cadastro de clientes
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroCliente.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
}
