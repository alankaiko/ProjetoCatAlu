package br.com.alucentro.api.repository.projecoes;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResumoProduto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;
	private String descricao;
	private BigDecimal peso;

	public ResumoProduto(Long id, String codigo, String descricao, BigDecimal peso) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.peso = peso;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPeso() {
		return peso;
	}
	
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
}
