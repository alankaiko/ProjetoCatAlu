package br.com.alucentro.api.dominio;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Imagem.class)
public abstract class Imagem_ {

	public static volatile SingularAttribute<Imagem, Produto> produto;
	public static volatile SingularAttribute<Imagem, String> nome;
	public static volatile SingularAttribute<Imagem, Long> id;
	public static volatile SingularAttribute<Imagem, String> caminho;

	public static final String PRODUTO = "produto";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CAMINHO = "caminho";

}

