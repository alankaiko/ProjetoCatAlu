package br.com.alucentro.api.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	public void salvarFoto(MultipartFile imagem) {
		this.salvar(this.diretorioFotos, imagem);
		//this.RedimensionarParaIcone(imagem);
	}
	
	private void salvar(String diretorio, MultipartFile arquivo) {
		
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());	

		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
	
	private void RedimensionarParaIcone( MultipartFile imagem) {
		Iterator<ImageWriter> iterador = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter subescreve = iterador.next();
		ImageWriteParam parametroimagem = subescreve.getDefaultWriteParam();
		parametroimagem.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		parametroimagem.setCompressionQuality(1);

		try {

			BufferedImage buffer = ImageIO.read(new ByteArrayInputStream(imagem.getBytes()));
			IIOImage imagestream = new IIOImage(buffer, null, null);

			File file = new File("C:\\aff");
			FileImageOutputStream output = new FileImageOutputStream(file);
			subescreve.setOutput(output);
			subescreve.write(null, imagestream, parametroimagem);
			subescreve.dispose();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
		
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
}
