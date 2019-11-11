package br.com.alucentro.api.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.eventos.RecursoCriadoEvent;
import br.com.alucentro.api.repository.filtro.ProdutoFilter;
import br.com.alucentro.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Produto> Listando(ProdutoFilter filtro, Pageable pageable) {
		return this.service.Listar(filtro, pageable);
	}
	
	
	@PostMapping("/anexo")
	public ResponseEntity<String> upload(@RequestParam MultipartFile foto) {
		String nomearquivo = this.service.salvarFoto(foto);
		System.out.println("veio aqui?");
		return ResponseEntity.status(HttpStatus.CREATED).body(nomearquivo);
	}
	
	@GetMapping("/down/{codigo}")
	public ResponseEntity<String> pegaimagem(@PathVariable String codigo){
		String img = this.service.PegarImagens(codigo);
		return ResponseEntity.ok(img);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName, HttpServletResponse response) {
		Path path = Paths.get("C:\\catalogo\\imagens\\" + fileName);
		Resource resource = null;
		System.out.println("rodou aqui");
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("image/jpeg"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
//	@GetMapping("/download/{codigo}")
//	public ResponseEntity<Resource> downloadFileFromLocal(@PathVariable String codigo) {
//		Resource resource = this.service.PegarImagem(codigo);
//
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(""))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//				.body(resource);
//	}
	
//	@GetMapping(value = "/download")
//	public ResponseEntity DownImagem(String nomearquivo, HttpServletResponse response) {
//		System.out.println("chamou o meotod");
//		HttpServletResponse resposta = this.service.downloadImageGroovy(response, nomearquivo);
//		return ResponseEntity.ok(resposta);
//	}
//	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	List<String> files = new ArrayList<String>();
//	 
//	  @PostMapping("/post")
//	  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
//	    String message = "";
//	    try {
//	      this.service.store(file);
//	      files.add(file.getOriginalFilename());
//	 
//	      message = "You successfully uploaded " + file.getOriginalFilename() + "!";
//	      return ResponseEntity.status(HttpStatus.OK).body(message);
//	    } catch (Exception e) {
//	      message = "FAIL to upload " + file.getOriginalFilename() + "!";
//	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//	    }
//	  }
//	 
//	  @GetMapping("/getallfiles")
//	  public ResponseEntity<List<String>> getListFiles(Model model) {
//	    List<String> fileNames = files.stream().map(fileName -> MvcUriComponentsBuilder
//	        .fromMethodName(ProdutoResource.class, "getFile", fileName).build().toString())
//	        .collect(Collectors.toList());
//	 
//	    return ResponseEntity.ok().body(fileNames);
//	  }
//	 
//	  @GetMapping("/files/{filename:.+}")
//	  @ResponseBody
//	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//	    Resource file = this.service.loadFile(filename);
//	    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	  }
	
}
