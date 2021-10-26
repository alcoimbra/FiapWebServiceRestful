package br.com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import br.com.entity.enums.PerfilEnum;

public class Funcionario implements Serializable {
	private static final long serialVersionUID = 8363293049753190009L;
	
	private Long id;
	private String name;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal valorHora;
	private Float quantidadeHorasTrabalhadaDia;
	private Float quantidadeHorasAlmoco;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> lancamentos;
	
	public Funcionario() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "valor_hora", nullable = true)
	public BigDecimal getValorHora() {
		return valorHora;
	}
	
	@Transient
	public Optional<BigDecimal> getValorOpt(){
		return Optional.ofNullable(valorHora);
	}
	
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}
	
	@Column(name = "quantidade_horas_trabalhada_dia", nullable = true)
	public Float getQuantidadeHorasTrabalhadaDia() {
		return quantidadeHorasTrabalhadaDia;
	}
	
	@Transient
	public Optional<Float> getQuantidadesHorasTrabalhadaDiaOpt(){
		return Optional.ofNullable(quantidadeHorasTrabalhadaDia);
	}
	
	public void setQuantidadeHorasTrabalhadaDia(Float quantidadeHorasTrabalhadaDia) {
		this.quantidadeHorasTrabalhadaDia = quantidadeHorasTrabalhadaDia;
	}
	
	@Column(name = "quantidades_horas_almoco", nullable = true)
	public Float getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}
	
	@Transient
	public Optional<Float> getQuantidadeHorasAlmocoOpt(){
		return Optional.ofNullable(quantidadeHorasAlmoco);
	}
	
	public void setQuantidadeHorasAlmoco(Float quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", name=" + name + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", quantidadeHorasTrabalhadaDia=" + quantidadeHorasTrabalhadaDia
				+ ", quantidadeHorasAlmoco=" + quantidadeHorasAlmoco + ", perfil=" + perfil + ", dataCriacao="
				+ dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", empresa=" + empresa + ", lancamentos="
				+ lancamentos + "]";
	}
}