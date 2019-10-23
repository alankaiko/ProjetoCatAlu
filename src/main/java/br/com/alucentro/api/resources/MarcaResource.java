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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alucentro.api.dominio.Marca;
import br.com.alucentro.api.eventos.RecursoCriadoEvent;
import br.com.alucentro.api.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaResource {
	@Autowired
	private MarcaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Marca> Listar() {
		return this.service.Listar();
	}
	
	@PostMapping
	public ResponseEntity<Marca> Salvar(@Valid @RequestBody Marca marca, HttpServletResponse resposta){
		Marca salvo = this.service.Criar(marca);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Marca> PorId(@PathVariable Long id){
		Marca salvo = this.service.BuscarPorId(id);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Marca> Atualizar(@PathVariable Long id, @Valid @RequestBody Marca marca){
		Marca salvo = this.service.Atualizar(id, marca);
		return ResponseEntity.ok(salvo);
	}
}
