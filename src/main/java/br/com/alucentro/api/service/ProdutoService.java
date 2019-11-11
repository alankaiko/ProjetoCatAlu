package br.com.alucentro.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.repository.ProdutoRepository;
import br.com.alucentro.api.repository.filtro.ProdutoFilter;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository dao;
	
	@Value("${produto.disco.raiz}")
	private String raiz;
	
	@Value("${produto.disco.diretorio-fotos}")
	private String diretorioFotos;
	
	@Value("${produto.disco.diretorio-icones}")
	private String diretorioIcones;
	
	
	public String salvarFoto(MultipartFile imagem) {
		return this.salvar(this.diretorioFotos, imagem);
	}
	
	private String salvar(String diretorio, MultipartFile arquivo) {
		String nomearquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
		
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());	

		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}	
		String arquivouri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/anexo/").path(nomearquivo).toUriString();
		System.out.println(arquivouri);
		return nomearquivo;
	}
	
	public HttpServletResponse downloadImageGroovy(String nomearquivo, HttpServletResponse response){
	try {
		ClassPathResource imageFile = new ClassPathResource(this.diretorioFotos + nomearquivo);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"sherlock_holmes.jpg\"");
        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
	} catch (Exception e) {
		System.out.println("deu nao");
	}
    return response;
}
	
	
	public String PegarImagens(String codigo) {
		String lista = null;
		String caminho = "C:\\catalogo\\imagens\\"+codigo;
		File arquivo = new File(caminho);
		System.out.println(caminho);
		if(arquivo != null) {
			String encode64 = null;
			
			try {
				FileInputStream stream = new FileInputStream(arquivo);
				byte[] bites = new byte[(int) arquivo.length()];
				stream.read(bites);
				encode64 = Base64.getEncoder().encodeToString(bites);
				lista = "data:image/jpeg; base64,"+ encode64;
				stream.close();
			} catch (Exception e) {
				System.out.println("erro");
			}
		}
		return lista;
	}
	
	
	
	public Page<Produto> Listar(ProdutoFilter filtro, Pageable pageable){
		return this.dao.Filtrando(filtro, pageable);
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
	
	
//	public Resource PegarImagem(String filename) {
//	Path path = Paths.get(filename);
//	System.out.println(filename);
//	Resource resource = null;
//	String contentType = "application/octet-stream";
//	try {
//		resource = new UrlResource(path.toUri());
//	} catch (Exception e) {e.printStackTrace();}
//	return resource;
//}
//
//public HttpServletResponse downloadImageGroovy(HttpServletResponse response, String nomearquivo){
//	try {
//		ClassPathResource imageFile = new ClassPathResource(this.diretorioFotos + nomearquivo);
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        response.setHeader("Content-Disposition", "attachment; filename=\"sherlock_holmes.jpg\"");
//        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
//	} catch (Exception e) {
//		System.out.println("deu nao");
//	}
//    return response;
//}

//@RequestMapping(value = "/show", method = RequestMethod.GET)
//void showImage(HttpServletResponse response){
//
//    ClassPathResource imageFile = new ClassPathResource("holmes.jpg")
//    response.setContentType(MediaType.IMAGE_JPEG_VALUE)
//    response.setHeader("Content-Disposition", "inline; filename=\"sherlock_holmes.jpg\"")
//    StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream())
//}
	
//	@PostMapping("/upload")
//	public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		
//		Path path = Paths.get(fileBasePath + fileName);
//		
//		try {
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/").path(fileName).toUriString();
//		return ResponseEntity.ok(fileDownloadUri);
//	}
//	
//	private final Path rootLocation = Paths.get("upload-dir");
//	
//	public void store(MultipartFile file) {
//	    try {
//	      Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//	    } catch (Exception e) {
//	      throw new RuntimeException("FAIL!");
//	    }
//	  }
//	 
//	  public Resource loadFile(String filename) {
//	    try {
//	      Path file = rootLocation.resolve(filename);
//	      Resource resource = new UrlResource(file.toUri());
//	      if (resource.exists() || resource.isReadable()) {
//	        return resource;
//	      } else {
//	        throw new RuntimeException("FAIL!");
//	      }
//	    } catch (MalformedURLException e) {
//	      throw new RuntimeException("FAIL!");
//	    }
//	  }
//	 
//	  public void deleteAll() {
//	    FileSystemUtils.deleteRecursively(rootLocation.toFile());
//	  }
//	 
//	  public void init() {
//	    try {
//	      Files.createDirectory(rootLocation);
//	    } catch (IOException e) {
//	      throw new RuntimeException("Could not initialize storage!");
//	    }
//	  
//	  }
}
