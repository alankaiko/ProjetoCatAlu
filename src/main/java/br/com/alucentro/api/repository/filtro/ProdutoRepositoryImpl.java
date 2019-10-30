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
import org.springframework.util.StringUtils;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.dominio.Produto_;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Produto> Filtrando(ProdutoFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);

		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Produto> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, pageable);
		
		return new PageImpl<>(tiped.getResultList(), pageable, Total(filtro));
	}
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, ProdutoFilter filtro, Root<Produto> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getCodigo()))
			lista.add(builder.like(builder.lower(root.get(Produto_.codigo)), "%"+ filtro.getCodigo().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getReferencia()))
			lista.add(builder.like(builder.lower(root.get(Produto_.referencia)), "%"+ filtro.getReferencia().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(Produto_.descricao)), "%"+ filtro.getDescricao().toLowerCase() + "%"));
		
		if(!StringUtils.isEmpty(filtro.getDescricaocomplementar()))
			lista.add(builder.like(builder.lower(root.get(Produto_.descricaocomplementar)), "%"+filtro.getDescricaocomplementar().toLowerCase()+"%"));
		
		
		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<Produto> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;
		
		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(ProdutoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Produto> root = query.from(Produto.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}















