package br.com.alucentro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.repository.ProdutoRepository;
import br.com.alucentro.api.repository.filtro.ProdutoFilter;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository dao;
	
	public List<Produto> Listar(ProdutoFilter filtro, Pageable pageable){
		return this.dao.findAll();
	}
	
	public Produto Criar(Produto produto) {
		return this.dao.save(produto);
	}
	
	public void Deletar(Long id) {
		this.dao.deleteById(id);
	}
	
	public Produto BuscarPorId(Long id) {
		Optional<Produto> salvo = this.dao.findById(id);
		
		if(salvo.get() == null)
			throw new EmptyResultDataAccessException(1);
		
		return salvo.get();
	}
	
	public Produto Atualizar(Long id, Produto produto) {
		Produto salvo = BuscarPorId(id);
		BeanUtils.copyProperties(produto, salvo, "id");
		return this.Criar(salvo);
	}
}
