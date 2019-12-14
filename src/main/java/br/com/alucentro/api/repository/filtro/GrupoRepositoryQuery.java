package br.com.alucentro.api.repository.filtro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Grupo;

public interface GrupoRepositoryQuery {
	public Page<Grupo> Filtrar(GrupoFilter filtro, Pageable page);
}
