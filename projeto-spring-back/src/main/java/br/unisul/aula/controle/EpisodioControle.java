package br.unisul.aula.controle;




import br.unisul.aula.dto.SerieDTO;
import br.unisul.aula.dto.TemporadaDTO;
import br.unisul.aula.model.Episodio;
import br.unisul.aula.model.Serie;
import br.unisul.aula.model.Temporada;
import br.unisul.aula.repositorio.EpisodioRepositorio;
import br.unisul.aula.repositorio.SerieRepositorio;
import br.unisul.aula.repositorio.TemporadaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/episodio")
public class EpisodioControle {

    @Autowired
    EpisodioRepositorio episodioRepositorio;

    @Autowired
    TemporadaRepositorio temporadaRepositorio;

    @Autowired
    SerieRepositorio serieRepositorio;

    @GetMapping("/todos")
    public List<Episodio> buscarTodos(){
        return this.episodioRepositorio.findAll();
    }

    @GetMapping("/episodioId/{id}")
    public Episodio getBuscaUmId(@PathVariable(value = "id")Long id){
        return this.episodioRepositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")
        );
    }

    @GetMapping("/temporadaSerie")
    public List<SerieDTO> buscarSerieTemporada(){
        List<Serie> serieList = this.serieRepositorio.findAllByContainsTemporada();
        List<SerieDTO> serieDTOList = new ArrayList<>();
        for (Serie serie : serieList){
            List<TemporadaDTO> temporadaDTOList = new ArrayList<>();
            List<Temporada> temporadaList = this.temporadaRepositorio.findAllBySerie(serie);
            for (Temporada temporada: temporadaList){
                temporadaDTOList.add(new TemporadaDTO(temporada.getId(), temporada.getNumero(), temporada.getDescricao()));
            }
            serieDTOList.add(new SerieDTO(serie.getId(), serie.getNome(), temporadaDTOList));
        }
        return serieDTOList;
    }

    @PostMapping("/criar")
    public Episodio criarEpisodio(@Validated @RequestBody Episodio episodio){
        return this.episodioRepositorio.save(episodio);
    }

    @PutMapping("/alterar/{id}")
    public Episodio alterarEpisodio(@PathVariable(value = "id")Long id, @Validated @RequestBody Episodio episodioNovo){
        Episodio episodio = this.getBuscaUmId(id);
        episodioNovo.setId(id);
        episodio.copiaAtributos(episodioNovo);
        return this.episodioRepositorio.save(episodio);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarEpisodio(@PathVariable(name = "id")Long id){
        Episodio episodio = this.getBuscaUmId(id);
        this.episodioRepositorio.delete(episodio);
        return ResponseEntity.ok().build();
    }
}
