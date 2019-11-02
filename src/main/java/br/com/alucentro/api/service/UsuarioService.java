package br.com.alucentro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alucentro.api.dominio.Usuario;
import br.com.alucentro.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository dao;
	
	public List<Usuario> ListarTodos() {
		return this.dao.findAll();
	}
	
	public Usuario Salvar(Usuario usuario) {
		return this.dao.save(usuario);
	}
	
	public void Deletar(Long id) {
		this.dao.deleteById(id);
	}
	
	public Usuario BuscarPorId(Long id) {
		Optional<Usuario> usuario = this.dao.findById(id);
		
		if(usuario.get() != null)
			throw new EmptyResultDataAccessException(1);
		
		return usuario.get();
	}
	
	public Usuario Atualizar(Long id, Usuario usuario) {
		Usuario salvo = this.BuscarPorId(id);
		BeanUtils.copyProperties(usuario, salvo,"id");
		return this.Salvar(salvo);
	}
}















