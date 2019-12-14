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

import br.com.alucentro.api.dominio.Grupo;
import br.com.alucentro.api.dominio.Grupo_;

public class GrupoRepositoryImpl implements GrupoRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Page<Grupo> Filtrar(GrupoFilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Grupo> query = builder.createQuery(Grupo.class);
		Root<Grupo> root = query.from(Grupo.class);
		
		query.orderBy(builder.asc(root.get("id")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Grupo> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);
		
		return new PageImpl<>(tiped.getResultList(), page, Total(filtro));
	}
	
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, GrupoFilter filtro, Root<Grupo> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getAbreviacao()))
			lista.add(builder.like(builder.lower(root.get(Grupo_.abreviacao)), "%"+ filtro.getAbreviacao().toLowerCase()+"%"));
		
		if(!StringUtils.isEmpty(filtro.getNomegrupo()))
			lista.add(builder.like(builder.lower(root.get(Grupo_.nomegrupo)), "%"+ filtro.getNomegrupo().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(GrupoFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Grupo> root = query.from(Grupo.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
	
}















