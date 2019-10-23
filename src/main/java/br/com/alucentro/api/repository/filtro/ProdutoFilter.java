package br.com.alucentro.api.repository.filtro;

public class ProdutoFilter {
	private String codigo;
	private String referencia;
	private String descricao;
	private String descricaocomplementar;
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaocomplementar() {
		return descricaocomplementar;
	}

	public void setDescricaocomplementar(String descricaocomplementar) {
		this.descricaocomplementar = descricaocomplementar;
	}

}
