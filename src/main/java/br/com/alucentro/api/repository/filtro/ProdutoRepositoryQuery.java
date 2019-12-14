package br.com.alucentro.api.repository.filtro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.repository.projecoes.ResumoProduto;

public interface ProdutoRepositoryQuery {
	public Page<Produto> Filtrando(ProdutoFilter filtro, Pageable pageable);
	public Page<ResumoProduto> resumir(ProdutoFilter filtro, Pageable pageable);
	public boolean VerificarSeCodigoExiste(String codigo);
}
