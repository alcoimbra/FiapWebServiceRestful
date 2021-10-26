package br.com.controller;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.FuncionarioDto;
import br.com.entity.Funcionario;
import br.com.response.Response;
import br.com.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
	private static final Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public FuncionarioController() {}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<FuncionarioDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando Funcionário: {}", funcionarioDto.toString());
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);
		if(!funcionario.isPresent()) {
			result.addError(new ObjectError("funcionario", "Funcionário não Encontrado"));
		}
		
		this.atualizarDadosFuncionario(funcionario.get(), funcionarioDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro Validando Funcionário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		this.funcionarioService.persist(funcionario.get());
		response.setData(this.converterFuncionarioDto(funcionario.get()));
		
		return ResponseEntity.ok(response);
	}
	
	private void atualizarDadosFuncionario(Funcionario funcionario, FuncionarioDto funcionarioDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		funcionario.setName(funcionarioDto.getNome());
		
		if(!funcionario.getEmail().equals(funcionarioDto.getEmail())) {
			this.funcionarioService.buscarPorEmail(funcionarioDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("email", "E-mail já Existente")));
			
			funcionario.setEmail(funcionarioDto.getEmail());
		}
		
		funcionario.setQuantidadeHorasAlmoco(null);
		funcionarioDto.getQuantidadeHorasAlmoco()
			.ifPresent(qtdHorasAlmoco -> funcionario.setQuantidadeHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		
		funcionario.setQuantidadeHorasTrabalhadaDia(null);
		funcionarioDto.getQuantidadeHorasTrabalhoDia()
			.ifPresent(qtdHorasTrabDia -> funcionario.setQuantidadeHorasTrabalhadaDia(Float.valueOf(qtdHorasTrabDia)));
		
		funcionario.setValorHora(null);
		funcionarioDto.getValorHora()
			.ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
	}
	
	private FuncionarioDto converterFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setNome(funcionario.getName());
		
		funcionario.getQuantidadeHorasAlmocoOpt()
			.ifPresent(qtdHorasAlmoco -> funcionarioDto.setQuantidadeHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		
		funcionario.getQuantidadesHorasTrabalhadaDiaOpt()
			.ifPresent(qtdHorasTrabDia -> funcionarioDto.setQuantidadeHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabDia))));
		
		funcionario.getValorOpt()
			.ifPresent(valorHora -> funcionarioDto.setValorHora(Optional.of(valorHora.toString())));
		
		return funcionarioDto;
	}
}