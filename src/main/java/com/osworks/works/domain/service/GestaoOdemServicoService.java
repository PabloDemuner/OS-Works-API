package com.osworks.works.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.osworks.works.domain.model.Cliente;
import com.osworks.works.domain.model.OrdemServico;
import com.osworks.works.domain.model.StatusOrdemServico;
//import com.osworks.works.domain.repository.ClienteRepository;
//import com.osworks.works.domain.repository.ClienteRepository;
import com.osworks.works.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	//private ClienteRepository clienteRepository;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		
		/*Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);*/
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	
}
