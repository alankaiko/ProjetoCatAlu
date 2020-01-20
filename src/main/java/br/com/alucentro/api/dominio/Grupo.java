package br.com.alucentro.api.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String abreviacao;
	private String nomegrupo;
	private String fperf;
	private String faces;
	private String fvidro;
	private String fchapa;
	private String ftrat;
	private String facessep;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public String getNomegrupo() {
		return nomegrupo;
	}

	public void setNomegrupo(String nomegrupo) {
		this.nomegrupo = nomegrupo;
	}

	public String getFperf() {
		return fperf;
	}

	public void setFperf(String fperf) {
		this.fperf = fperf;
	}

	public String getFaces() {
		return faces;
	}

	public void setFaces(String faces) {
		this.faces = faces;
	}

	public String getFvidro() {
		return fvidro;
	}

	public void setFvidro(String fvidro) {
		this.fvidro = fvidro;
	}

	public String getFchapa() {
		return fchapa;
	}

	public void setFchapa(String fchapa) {
		this.fchapa = fchapa;
	}

	public String getFtrat() {
		return ftrat;
	}

	public void setFtrat(String ftrat) {
		this.ftrat = ftrat;
	}

	public String getFacessep() {
		return facessep;
	}

	public void setFacessep(String facessep) {
		this.facessep = facessep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
