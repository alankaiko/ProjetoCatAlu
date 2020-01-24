package br.com.alucentro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alucentro.api.dominio.Marca;
import br.com.alucentro.api.repository.MarcaRepository;
import br.com.alucentro.api.repository.filtro.MarcaFilter;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository dao;
	
	public List<Marca> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Marca> Filtrando(MarcaFilter filtro, Pageable page) {
		return this.dao.Filtrar(filtro, page);
	}
	
	public Marca Criar(Marca marca) {
		return this.dao.save(marca);
	}
	
	public void Deletar(Long id) {
		this.dao.deleteById(id);
	}
	
	public Marca BuscarPorId(Long id) {
		Optional<Marca> marca = this.dao.findById(id);
		
		if(marca.get() == null)
			throw new EmptyResultDataAccessException(1);
		
		return marca.get();
	}
	
	public Marca Atualizar(Long id, Marca marca) {
		Marca salvo = this.BuscarPorId(id);
		BeanUtils.copyProperties(marca, salvo,"id");
		return this.Criar(salvo);
	}
}














