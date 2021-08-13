package br.unisul.aula.repositorio;

import br.unisul.aula.model.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodioRepositorio extends JpaRepository<Episodio, Long> {
}
