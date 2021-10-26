package br.com.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entity.Funcionario;
import br.com.repository.FuncionarioRepository;
import br.com.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public Funcionario persist(Funcionario funcionario) {
		log.info("Persistindo Funcionario: {}", funcionario);
		
		return this.funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando Funcionario pelo CPF: {}", cpf);
		
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando Funcionario pelo E-mail: {}", email);
		
		return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando Funcionario pelo ID: {}", id);
		
		return Optional.ofNullable(this.funcionarioRepository.findById(id).get());
	}
}
