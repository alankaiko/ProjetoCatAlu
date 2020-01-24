package br.com.alucentro.api.dominio;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class PropriedadesProduto {
	private BigDecimal custo;
	private BigDecimal preco;
	private String unidade;
	private BigDecimal peso;
	private BigDecimal tamanho;
	private boolean estoque;

	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public BigDecimal getTamanho() {
		return tamanho;
	}

	public void setTamanho(BigDecimal tamanho) {
		this.tamanho = tamanho;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public boolean isEstoque() {
		return estoque;
	}

	public void setEstoque(boolean estoque) {
		this.estoque = estoque;
	}
}
