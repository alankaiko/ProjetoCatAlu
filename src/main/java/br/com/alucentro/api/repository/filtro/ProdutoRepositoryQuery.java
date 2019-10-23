package br.com.alucentro.api.repository.filtro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Produto;

public interface ProdutoRepositoryQuery {
	public Page<Produto> Filtrando(ProdutoFilter filtro, Pageable pageable);
}
