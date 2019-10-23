package br.com.alucentro.api.dominio;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public abstract class Grupo_ {

	public static volatile SingularAttribute<Grupo, String> abreviacao;
	public static volatile SingularAttribute<Grupo, String> nomeGrupo;
	public static volatile SingularAttribute<Grupo, Long> id;

	public static final String ABREVIACAO = "abreviacao";
	public static final String NOME_GRUPO = "nomeGrupo";
	public static final String ID = "id";

}

