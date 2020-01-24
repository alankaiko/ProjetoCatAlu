package br.com.alucentro.api.repository.filtro;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Linha;

public interface LinhaRepositoryQuery {
	public Page<Linha> Filtrando(LinhaFilter filtro, Pageable page);
}
