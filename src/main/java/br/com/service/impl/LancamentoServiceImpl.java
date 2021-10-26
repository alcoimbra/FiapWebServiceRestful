package br.com.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.entity.Lancamento;
import br.com.repository.LancamentoRepository;
import br.com.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando Lançamentos para o Funcionário ID {}", funcionarioId);
		
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um Lançamento pelo ID {}", id);
		
		return Optional.ofNullable(this.lancamentoRepository.findById(id)).get();
	}

	@Override
	public Lancamento persist(Lancamento lancamento) {
		log.info("Persistindo o Lançamento: {}", lancamento);
		
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o Lançamento ID {}", id);
		this.lancamentoRepository.deleteById(id);
	}
}