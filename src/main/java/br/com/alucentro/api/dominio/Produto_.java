package br.com.alucentro.api.dominio;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> codigo;
	public static volatile SingularAttribute<Produto, String> descricaocomplementar;
	public static volatile SingularAttribute<Produto, BigDecimal> peso;
	public static volatile SingularAttribute<Produto, Date> datainclusao;
	public static volatile SingularAttribute<Produto, Grupo> grupo;
	public static volatile SingularAttribute<Produto, Imagem> imagem;
	public static volatile SingularAttribute<Produto, Linha> linha;
	public static volatile SingularAttribute<Produto, String> unidade;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, BigDecimal> preco;
	public static volatile SingularAttribute<Produto, Marca> marca;
	public static volatile SingularAttribute<Produto, Boolean> estoque;
	public static volatile SingularAttribute<Produto, BigDecimal> tamanho;
	public static volatile SingularAttribute<Produto, BigDecimal> custo;
	public static volatile SingularAttribute<Produto, Departamento> departamento;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> referencia;
	public static volatile SingularAttribute<Produto, Boolean> status;

	public static final String CODIGO = "codigo";
	public static final String DESCRICAOCOMPLEMENTAR = "descricaocomplementar";
	public static final String PESO = "peso";
	public static final String DATAINCLUSAO = "datainclusao";
	public static final String GRUPO = "grupo";
	public static final String IMAGEM = "imagem";
	public static final String LINHA = "linha";
	public static final String UNIDADE = "unidade";
	public static final String DESCRICAO = "descricao";
	public static final String PRECO = "preco";
	public static final String MARCA = "marca";
	public static final String ESTOQUE = "estoque";
	public static final String TAMANHO = "tamanho";
	public static final String CUSTO = "custo";
	public static final String DEPARTAMENTO = "departamento";
	public static final String ID = "id";
	public static final String REFERENCIA = "referencia";
	public static final String STATUS = "status";

}

