package com.osworks.works.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osworks.works.api.model.Comentario;
import com.osworks.works.domain.model.Cliente;
//import com.osworks.works.domain.model.Cliente;
import com.osworks.works.domain.model.OrdemServico;
import com.osworks.works.domain.model.StatusOrdemServico;
import com.osworks.works.domain.repository.ClienteRepository;
import com.osworks.works.domain.repository.ComentarioRepository;
//import com.osworks.works.domain.repository.ClienteRepository;
//import com.osworks.works.domain.repository.ClienteRepository;
import com.osworks.works.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(null);
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar (ordemServicoId);
				
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
		
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(null);
	}
	
}
