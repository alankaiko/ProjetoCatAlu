package br.com.alucentro.api.dominio;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> codigo;
	public static volatile SingularAttribute<Produto, String> descricaocomplementar;
	public static volatile SingularAttribute<Produto, LocalDate> datainclusao;
	public static volatile SingularAttribute<Produto, Cor> cor;
	public static volatile SingularAttribute<Produto, Grupo> grupo;
	public static volatile SingularAttribute<Produto, Imagem> imagem;
	public static volatile SingularAttribute<Produto, Linha> linha;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, Marca> marca;
	public static volatile SingularAttribute<Produto, Departamento> departamento;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> referencia;
	public static volatile SingularAttribute<Produto, PropriedadesProduto> propriedades;
	public static volatile SingularAttribute<Produto, Boolean> status;

	public static final String CODIGO = "codigo";
	public static final String DESCRICAOCOMPLEMENTAR = "descricaocomplementar";
	public static final String DATAINCLUSAO = "datainclusao";
	public static final String COR = "cor";
	public static final String GRUPO = "grupo";
	public static final String IMAGEM = "imagem";
	public static final String LINHA = "linha";
	public static final String DESCRICAO = "descricao";
	public static final String MARCA = "marca";
	public static final String DEPARTAMENTO = "departamento";
	public static final String ID = "id";
	public static final String REFERENCIA = "referencia";
	public static final String PROPRIEDADES = "propriedades";
	public static final String STATUS = "status";

}

