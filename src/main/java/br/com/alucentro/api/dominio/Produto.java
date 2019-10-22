package br.com.alucentro.api.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private List<Imagem> listaCaminhoImagem;
	private LocalDate datainclusao;
	private boolean estoque;
	private boolean status;


	public Produto() {
		this.listaCaminhoImagem = new ArrayList<Imagem>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_grupo_id", referencedColumnName = "id")
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_linha_id", referencedColumnName = "id")
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

	@OneToMany
	public List<Imagem> getListaCaminhoImagem() {
		return listaCaminhoImagem;
	}

	public void setListaCaminhoImagem(List<Imagem> listaCaminhoImagem) {
		this.listaCaminhoImagem = listaCaminhoImagem;
	}

	public LocalDate getDatainclusao() {
		return datainclusao;
	}

	public void setDatainclusao(LocalDate datainclusao) {
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
