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

import br.com.alucentro.api.dominio.Marca;
import br.com.alucentro.api.dominio.Marca_;

public class MarcaRepositoryImpl implements MarcaRepositoryQuery{
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Page<Marca> Filtrar(MarcaFilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Marca> query = builder.createQuery(Marca.class);
		Root<Marca> root = query.from(Marca.class);
		
		query.orderBy(builder.asc(root.get("id")));
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		
		TypedQuery<Marca> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);
		
		return new PageImpl<>(tiped.getResultList(), page, Total(filtro));
	}
	
	
	private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, MarcaFilter filtro, Root<Marca> root) {
		List<Predicate> lista= new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(filtro.getMarca()))
			lista.add(builder.like(builder.lower(root.get(Marca_.marca)), "%"+ filtro.getMarca().toLowerCase()+"%"));
		
		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}
	
	private Long Total(MarcaFilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Marca> root = query.from(Marca.class);
		
		Predicate[] predicato = AdicionarRestricoes(builder, filtro, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}
	
}
