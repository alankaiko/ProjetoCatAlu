package br.com.alucentro.api.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.alucentro.api.dominio.Produto;
import br.com.alucentro.api.repository.ProdutoRepository;
import br.com.alucentro.api.repository.filtro.ProdutoFilter;
import br.com.alucentro.api.repository.projecoes.ResumoProduto;

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
    
	public boolean VerificaSeExisteCodigo(String codigo) {
		return this.dao.VerificarSeCodigoExiste(codigo);
	}
	
	public Page<Produto> Listar(ProdutoFilter filtro, Pageable pageable){
		return this.dao.Filtrando(filtro, pageable);
	}
	
	public Page<ResumoProduto> Resume(ProdutoFilter filtro, Pageable page) {
		return this.dao.resumir(filtro, page);
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
		BeanUtils.copyProperties(produto, salvo, "id","codigo");
		return this.Criar(salvo);
	}
	
	public void SalvarFoto(MultipartFile imagem) {
		Path dir = this.SalvarImagem(this.diretorioFotos, imagem);
		String nome = this.CorrigeBarra(imagem.getOriginalFilename());
		this.RedimensionarParaIcone(dir, nome, 400, 400);
	}

	private Path SalvarImagem(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		String codigo = CorrigeBarra(arquivo.getOriginalFilename());
		Path arquivoPath = diretorioPath.resolve(codigo);
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());	

		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}	
		
		return arquivoPath;
	}
    
    private void RedimensionarParaIcone(Path image, String nome, int new_w, int new_h) {
    	Path diretorioPath = Paths.get(this.raiz, this.diretorioIcones);
    	String codigo = CorrigeBarra(nome);
		File arquivo = image.toFile();    	
		
    	try {
    		Files.createDirectories(diretorioPath);
    		
            BufferedImage imagem = ImageIO.read(arquivo);
            BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, new_w, new_h, null);
            g.dispose();
            
            ImageIO.write(new_img, "JPG", new File(diretorioPath+"\\"+ "a_"+ codigo));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
	}
    
    public byte[] BuscarImagem(String codigo){
    	Path diretorioPath = Paths.get(this.raiz, this.diretorioFotos);
    	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(diretorioPath +"\\"+ codigo + ".jpeg");
    		bytes = StreamUtils.copyToByteArray(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
    
   
    
    public byte[] BuscarIcones(String codigo) {
    	Path diretorioPath = Paths.get(this.raiz, this.diretorioIcones);
    	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(diretorioPath +"\\a_"+ codigo + ".jpeg");
    		
    		bytes = StreamUtils.copyToByteArray(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
    
    private String CorrigeBarra(String codigo) {
    	codigo = codigo.replaceAll("/", "-");
    	return codigo;
    }
}
