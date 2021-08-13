package br.unisul.aula.repositorio;

import br.unisul.aula.dto.TemporadaDTO;
import br.unisul.aula.model.Serie;
import br.unisul.aula.model.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporadaRepositorio extends JpaRepository<Temporada, Long> {

    List<Temporada> findAllBySerie(Serie serie);
}
