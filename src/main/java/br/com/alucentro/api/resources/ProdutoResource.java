package br.com.alucentro.api.resources;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.eventos.RecursoCriadoEvent;
import br.com.alucentro.api.repository.filtro.ProdutoFilter;
import br.com.alucentro.api.repository.projecoes.ResumoProduto;
import br.com.alucentro.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/vefifica/{codigo}")
	public boolean VerificaCodigo(@PathVariable String codigo) {
		return this.service.VerificaSeExisteCodigo(codigo);
	}
	
	@GetMapping
	public Page<Produto> Listando(ProdutoFilter filtro, Pageable pageable) {
		return this.service.Listar(filtro, pageable);
	}
	
	@GetMapping(params = "resumo")
	public Page<ResumoProduto> Resumir(ProdutoFilter filtro, Pageable page) {
		return this.service.Resume(filtro, page);
	}
	
	@PostMapping("/anexo")
	public void upload(@RequestParam MultipartFile foto) {
		this.service.SalvarFoto(foto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> Salvar(@Valid @RequestBody Produto produto, HttpServletResponse resposta){
		Produto salvo = this.service.Criar(produto);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long id) {
		this.service.Deletar(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> PorId(@PathVariable Long id){
		Produto salvo = this.service.BuscarPorId(id);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> Atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto){
		Produto salvo = this.service.Atualizar(id, produto);
		return ResponseEntity.ok(salvo);
	}
	
	@GetMapping(value = "/imagem/{codigo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> BuscarImagem(@PathVariable String codigo) throws IOException {
    	byte[] bytes = this.service.BuscarImagem(codigo);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    
    @GetMapping(value = "/icone/{codigo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> BuscarIcone(@PathVariable String codigo) throws IOException {
        byte[] bytes = this.service.BuscarIcones(codigo);    
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    
//    @GetMapping(value = "/primeiraimagem", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void getImage(HttpServletResponse response) throws IOException {
//        ClassPathResource imgFile = new ClassPathResource("catalogo/imagens/algar.jpeg");
//
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
//	}
//   
//    @GetMapping(value = "/terceiraimagem", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<InputStreamResource> getImagem() throws IOException {
//    	ClassPathResource imgFile = new ClassPathResource("catalogo/imagens/algar.jpeg");
//
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(imgFile.getInputStream()));
//    }
 
	
}
