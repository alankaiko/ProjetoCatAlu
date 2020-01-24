package br.com.alucentro.api.repository.projecoes;

import java.io.Serializable;

public class ResumoProduto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;
	private String descricao;

	public ResumoProduto(Long id, String codigo, String descricao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
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

}
