package br.unisul.aula.controle;


import br.unisul.aula.model.Serie;
import br.unisul.aula.repositorio.SerieRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sun.rmi.runtime.Log;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/serie")
public class SerieControle {

    @Autowired
    SerieRepositorio serieRepositorio;


    @GetMapping("/todas")
    public List<Serie> getTodasSeries(){
        return this.serieRepositorio.findAll();
    }

    @GetMapping("/temporadas")
    public List<Serie> getBuscarSerieContaimTemporada(){
        return this.serieRepositorio.findAllByContainsTemporada();
    }

    @GetMapping("/nome/{nome}")
    public List<Serie> buscarPorNome(@PathVariable(value = "nome")String nome){
        return this.serieRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/serieId/{id}")
    public Serie getBuscaUmId(@PathVariable(value = "id")Long id){
        return this.serieRepositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")
        );
    }

    @PostMapping("/criar")
    public Serie criarSerie(@Validated @RequestBody Serie serie){
        return this.serieRepositorio.save(serie);
    }

    @PutMapping("/alterar/{id}")
    public Serie alterarSerie(@PathVariable(value = "id")Long id, @Validated @RequestBody Serie serieNovo){
        Serie serie = this.getBuscaUmId(id);
        serieNovo.setId(id);
        serie.copiaAtributos(serieNovo);
        return this.serieRepositorio.save(serie);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarSerie(@PathVariable(name = "id")Long id){
        Serie serie = this.getBuscaUmId(id);
        this.serieRepositorio.delete(serie);
        return ResponseEntity.ok().build();
    }
}
