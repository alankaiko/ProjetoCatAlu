package br.com.alucentro.api.dominio;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PropriedadesProduto.class)
public abstract class PropriedadesProduto_ {

	public static volatile SingularAttribute<PropriedadesProduto, BigDecimal> preco;
	public static volatile SingularAttribute<PropriedadesProduto, Boolean> estoque;
	public static volatile SingularAttribute<PropriedadesProduto, BigDecimal> tamanho;
	public static volatile SingularAttribute<PropriedadesProduto, BigDecimal> peso;
	public static volatile SingularAttribute<PropriedadesProduto, BigDecimal> custo;
	public static volatile SingularAttribute<PropriedadesProduto, String> unidade;

	public static final String PRECO = "preco";
	public static final String ESTOQUE = "estoque";
	public static final String TAMANHO = "tamanho";
	public static final String PESO = "peso";
	public static final String CUSTO = "custo";
	public static final String UNIDADE = "unidade";

}

