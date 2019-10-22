package br.com.alucentro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alucentro.api.dominio.Linha;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Long>{

}
