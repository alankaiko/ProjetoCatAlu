package br.com.alucentro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alucentro.api.dominio.Grupo;
import br.com.alucentro.api.repository.GrupoRepository;
import br.com.alucentro.api.repository.filtro.GrupoFilter;

@Service
public class GrupoService {
	@Autowired
	private GrupoRepository dao;
	
	public List<Grupo> ListarTodos(){
		return this.dao.findAll();
	}
	
	public Page<Grupo> Filtrando(GrupoFilter filtro, Pageable page) {
		return this.dao.Filtrar(filtro, page);
	}
	
	public Grupo Criar(Grupo grupo) {
		return this.dao.save(grupo);
	}
	
	public void Deletar(Long id) {
		this.dao.deleteById(id);
	}
	
	public Grupo BuscarPorId(Long id) {
		Optional<Grupo> grupo = this.dao.findById(id);
		
		if(grupo.get() == null)
			throw new EmptyResultDataAccessException(1);
		
		return grupo.get();
	}
	
	public Grupo Atualizar(Long id, Grupo grupo) {
		Grupo salvo = this.BuscarPorId(id);
		BeanUtils.copyProperties(grupo, salvo, "id");
		return this.Criar(salvo);
	}
}
