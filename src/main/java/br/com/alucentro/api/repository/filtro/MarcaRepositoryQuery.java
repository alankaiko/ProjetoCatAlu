package br.com.alucentro.api.repository.filtro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Marca;

public interface MarcaRepositoryQuery {
	public Page<Marca> Filtrar(MarcaFilter filtro, Pageable page);
}
