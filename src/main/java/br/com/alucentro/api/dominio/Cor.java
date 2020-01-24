package br.com.alucentro.api.dominio;

import javax.persistence.Embeddable;

@Embeddable
public class Cor {
	private String cor;
	private String cordescricao;

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCordescricao() {
		return cordescricao;
	}

	public void setCordescricao(String cordescricao) {
		this.cordescricao = cordescricao;
	}
}
