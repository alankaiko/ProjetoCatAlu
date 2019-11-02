package br.com.alucentro.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alucentro.api.dominio.Usuario;
import br.com.alucentro.api.eventos.RecursoCriadoEvent;
import br.com.alucentro.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Usuario> Listar(){
		return this.service.ListarTodos();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> Criar(@Valid @RequestBody Usuario usuario, HttpServletResponse resposta) {
		Usuario salvo = this.service.Salvar(usuario);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> PorId(@PathVariable Long id) {
		Usuario usuario = this.service.BuscarPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> Atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
		Usuario salvo = this.service.Atualizar(id, usuario);
		return ResponseEntity.ok(salvo);
	}
}
