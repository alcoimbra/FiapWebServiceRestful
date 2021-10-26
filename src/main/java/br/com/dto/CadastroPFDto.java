package br.com.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroPFDto {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> quantidadeHorasTrabalhoDia = Optional.empty();
	private Optional<String> quantidadeHorasAlmoco = Optional.empty();
	private String cnpj;
	
	public CadastroPFDto() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome não pode ser Vazio")
	@Length(min=3, max=200, message = "Nome deve conter entre 3 e 200 Caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message = "E-mail não pode ser Vazio")
	@Length(min=5, max=200, message = "E-mail deve conter entre 5 e 200 Caracteres")
	@Email(message = "E-mail Inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty(message = "Senha não pode ser Vazia")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotEmpty(message = "CPF não pode ser Inválido")
	@CPF(message = "CPF Inválido")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQuantidadeHorasTrabalhoDia() {
		return quantidadeHorasTrabalhoDia;
	}

	public void setQuantidadeHorasTrabalhoDia(Optional<String> quantidadeHorasTrabalhoDia) {
		this.quantidadeHorasTrabalhoDia = quantidadeHorasTrabalhoDia;
	}

	public Optional<String> getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}

	public void setQuantidadeHorasAlmoco(Optional<String> quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}
	
	@NotEmpty(message = "CNPJ não pode ser Vazio")
	@CNPJ(message = "CNPJ Inválido")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPFDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", quantidadeHorasTrabalhoDia=" + quantidadeHorasTrabalhoDia
				+ ", quantidadeHorasAlmoco=" + quantidadeHorasAlmoco + ", cnpj=" + cnpj + "]";
	}
}