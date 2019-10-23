package br.com.alucentro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alucentro.api.dominio.Linha;
import br.com.alucentro.api.repository.LinhaRepository;

@Service
public class LinhaService {
	@Autowired
	private LinhaRepository dao;
	
	public List<Linha> Listar() {
		return this.dao.findAll();
	}
	
	public Linha Criar(Linha linha) {
		return this.dao.save(linha);
	}
	
	public void Deletar(Long id) {
		this.dao.deleteById(id);
	}
	
	public Linha BuscarPorId(Long id) {
		Optional<Linha> linha = this.dao.findById(id);
		
		if(linha.get() == null)
			throw new EmptyResultDataAccessException(1);
		
		return linha.get();
	}
	
	public Linha Atualizar(Long id, Linha linha) {
		Linha salvo = this.BuscarPorId(id);
		BeanUtils.copyProperties(linha, salvo,"id");
		return this.Criar(salvo);
	}
}





















