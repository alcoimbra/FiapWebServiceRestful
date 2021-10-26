package br.com.service;

import java.util.Optional;

import br.com.entity.Empresa;

public interface EmpresaService {
	
	Optional<Empresa> buscarPorCnpj(String cnpj);
	Empresa persist(Empresa empresa);
}