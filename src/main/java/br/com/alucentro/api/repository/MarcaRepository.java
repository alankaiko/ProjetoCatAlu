package br.com.alucentro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alucentro.api.dominio.Marca;
import br.com.alucentro.api.repository.filtro.MarcaRepositoryQuery;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>, MarcaRepositoryQuery{

}
