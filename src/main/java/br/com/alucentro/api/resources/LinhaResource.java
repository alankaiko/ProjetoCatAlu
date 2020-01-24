package br.com.alucentro.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alucentro.api.dominio.Linha;
import br.com.alucentro.api.eventos.RecursoCriadoEvent;
import br.com.alucentro.api.repository.filtro.LinhaFilter;
import br.com.alucentro.api.service.LinhaService;

@RestController
@RequestMapping("/linhas")
@CrossOrigin("*")
public class LinhaResource {
	@Autowired
	private LinhaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Linha> ListarTodos() {
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<Linha> Filtrado(LinhaFilter filtro, Pageable page){
		return this.service.Filtrando(filtro, page);
	}

	@PostMapping
	public ResponseEntity<Linha> Salvar(@Valid @RequestBody Linha linha, HttpServletResponse resposta) {
		Linha salvo = this.service.Criar(linha);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Linha> PorId(@PathVariable Long id) {
		Linha salvo = this.service.BuscarPorId(id);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Linha> Atualizar(@PathVariable Long id, @Valid @RequestBody Linha linha) {
		Linha salvo = this.service.Atualizar(id, linha);
		return ResponseEntity.ok(salvo);
	}
}
