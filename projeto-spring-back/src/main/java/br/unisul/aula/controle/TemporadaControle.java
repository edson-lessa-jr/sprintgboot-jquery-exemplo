package br.unisul.aula.controle;

import br.unisul.aula.model.Temporada;
import br.unisul.aula.model.Temporada;
import br.unisul.aula.repositorio.TemporadaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/temporada")
public class TemporadaControle {

    @Autowired
    TemporadaRepositorio temporadaRepositorio;

    @GetMapping("/todos")
    public List<Temporada> buscarTodos(){
        return this.temporadaRepositorio.findAll();
    }

    @GetMapping("/temporadaId/{id}")
    public Temporada getBuscaUmId(@PathVariable(value = "id")Long id){
        return this.temporadaRepositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado")
        );
    }

    @PostMapping("/criar")
    public Temporada criarTemporada(@Validated @RequestBody Temporada temporada){
        return this.temporadaRepositorio.save(temporada);
    }

    @PutMapping("/alterar/{id}")
    public Temporada alterarTemporada(@PathVariable(value = "id")Long id, @Validated @RequestBody Temporada temporadaNovo){
        Temporada temporada = this.getBuscaUmId(id);
        temporadaNovo.setId(id);
        temporada.copiaAtributos(temporadaNovo);
        return this.temporadaRepositorio.save(temporada);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarTemporada(@PathVariable(name = "id")Long id){
        Temporada temporada = this.getBuscaUmId(id);
        this.temporadaRepositorio.delete(temporada);
        return ResponseEntity.ok().build();
    }
}
