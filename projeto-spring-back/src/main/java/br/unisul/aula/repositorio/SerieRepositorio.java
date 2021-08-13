package br.unisul.aula.repositorio;

import br.unisul.aula.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepositorio extends JpaRepository<Serie, Long> {

    @Query(value = "select * from tb_series s where exists(select 1 from tb_temporadas t where s.id=t.serie_id)",
    nativeQuery = true)
    List<Serie> findAllByContainsTemporada();

    List<Serie> findByNomeContainingIgnoreCase(String nome);
}
