package br.com.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.entity.Lancamento;

public interface LancamentoService {
	
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	Optional<Lancamento> buscarPorId(Long id);
	Lancamento persist(Lancamento lancamento);
	void remover(Long id);
}