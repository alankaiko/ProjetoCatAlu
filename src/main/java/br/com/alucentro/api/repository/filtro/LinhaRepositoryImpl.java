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

import br.com.alucentro.api.dominio.Linha;
import br.com.alucentro.api.dominio.Linha_;

public class LinhaRepositoryImpl implements LinhaRepositoryQuery {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Linha> Filtrando(Linhafilter filtro, Pageable page) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Linha> query = builder.createQuery(Linha.class);
		Root<Linha> root = query.from(Linha.class);

		Predicate[] predicato = AdicionarRestricoes(filtro, builder, root);
		query.where(predicato);

		TypedQuery<Linha> tiped = em.createQuery(query);
		AdicionarPaginacao(tiped, page);

		return new PageImpl<>(tiped.getResultList(), page, Total(filtro));
	}

	private Predicate[] AdicionarRestricoes(Linhafilter filtro, CriteriaBuilder builder, Root<Linha> root) {
		List<Predicate> lista = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(filtro.getNomelinha()))
			lista.add(builder.like(builder.lower(root.get(Linha_.nomelinha)),
					"%" + filtro.getNomelinha().toLowerCase() + "%"));

		if (!StringUtils.isEmpty(filtro.getDescricao()))
			lista.add(builder.like(builder.lower(root.get(Linha_.descricao)),
					"%" + filtro.getDescricao().toLowerCase() + "%"));

		if (!StringUtils.isEmpty(filtro.getCor()))
			lista.add(builder.like(builder.lower(root.get(Linha_.cor)), "%" + filtro.getCor().toLowerCase() + "%"));

		return lista.toArray(new Predicate[lista.size()]);
	}

	private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
		int paginaatual = page.getPageNumber();
		int totalporpagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaatual * totalporpagina;

		tiped.setFirstResult(primeiroRegistroDaPagina);
		tiped.setMaxResults(totalporpagina);
	}

	private Long Total(Linhafilter filtro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Linha> root = query.from(Linha.class);

		Predicate[] predicato = AdicionarRestricoes(filtro, builder, root);
		query.where(predicato);
		query.select(builder.count(root));
		return em.createQuery(query).getSingleResult();
	}

}
