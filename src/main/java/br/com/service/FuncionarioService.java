package br.com.service;

import java.util.Optional;

import br.com.entity.Funcionario;

public interface FuncionarioService {
	
	Funcionario persist(Funcionario funcionario);
	Optional<Funcionario> buscarPorCpf(String cpf);
	Optional<Funcionario> buscarPorEmail(String email);
	Optional<Funcionario> buscarPorId(Long id);
}