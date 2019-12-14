package br.com.alucentro.api.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private String referencia;
	private String descricao;
	private String descricaocomplementar;
	private BigDecimal custo;
	private BigDecimal preco;
	private Grupo grupo;
	private String unidade;
	private Linha linha;
	private Marca marca;
	private BigDecimal peso;
	private BigDecimal tamanho;
	private Imagem imagem;
	private Date datainclusao;
	private boolean estoque;
	private boolean status;
	private Departamento departamento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "codigo", unique = true)
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_grupo_id", referencedColumnName = "id", nullable = true)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_linha_id", referencedColumnName = "id", nullable = true)
	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_marca_id", referencedColumnName = "id")
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_imagem_id", referencedColumnName = "id")
	public Imagem getImagem() {
		return imagem;
	}
	
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	@Temporal(TemporalType.DATE)
	public Date getDatainclusao() {
		return datainclusao;
	}
	
	public void setDatainclusao(Date datainclusao) {
		this.datainclusao = datainclusao;
	}

	public boolean isEstoque() {
		return estoque;
	}

	public void setEstoque(boolean estoque) {
		this.estoque = estoque;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_departamento_id", referencedColumnName = "id")
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
