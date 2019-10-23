package br.com.alucentro.api.repository.filtro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Produto;

public class ProdutoFilterImpl implements ProdutoFilterQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Produto> Filtrando(ProdutoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);

		Predicate[] predicato = AdicionarRestricoes(builder, pageable, root);
		query.where(predicato);
		
		TypedQuery<Produto> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, Pageable pageable, Root<Produto> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		
		
		
		return null;
	}

	private Long AdicionarPaginacao(TypedQuery<Produto> tiped, Pageable pageable) {
		
		return null;
	}
	
	private Long Total(ProdutoFilter filtro) {
		
		return null;
	}
}















