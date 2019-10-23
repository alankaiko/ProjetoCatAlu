package br.com.alucentro.api.dominio;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Linha.class)
public abstract class Linha_ {

	public static volatile SingularAttribute<Linha, String> cor;
	public static volatile SingularAttribute<Linha, String> nomeLinha;
	public static volatile SingularAttribute<Linha, Long> id;

	public static final String COR = "cor";
	public static final String NOME_LINHA = "nomeLinha";
	public static final String ID = "id";

}

