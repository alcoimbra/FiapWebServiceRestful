package br.com.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class FuncionarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private Optional<String> senha = Optional.empty();
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> quantidadeHorasTrabalhoDia = Optional.empty();
	private Optional<String> quantidadeHorasAlmoco = Optional.empty();
	
	public FuncionarioDto() {}

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

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
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

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", quantidadeHorasTrabalhoDia=" + quantidadeHorasTrabalhoDia + ", quantidadeHorasAlmoco="
				+ quantidadeHorasAlmoco + "]";
	}
}