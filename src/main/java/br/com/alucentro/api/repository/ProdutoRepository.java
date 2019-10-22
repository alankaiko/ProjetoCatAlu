package br.com.alucentro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alucentro.api.dominio.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
