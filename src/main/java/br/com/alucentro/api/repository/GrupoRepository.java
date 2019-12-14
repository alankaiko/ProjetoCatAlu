package br.com.alucentro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alucentro.api.dominio.Grupo;
import br.com.alucentro.api.repository.filtro.GrupoRepositoryQuery;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryQuery{

}
