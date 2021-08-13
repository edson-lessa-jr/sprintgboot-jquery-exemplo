package br.unisul.aula.dto;

import br.unisul.aula.model.Temporada;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

public class SerieDTO {

    @JsonIgnore
    private long id;
    private String nome;

    private List<TemporadaDTO> temporadas;


    public SerieDTO(long id, String nome, List<TemporadaDTO> temporadas) {
        this.id = id;
        this.nome = nome;
        this.temporadas = temporadas;
    }

    public SerieDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TemporadaDTO> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<TemporadaDTO> temporadas) {
        this.temporadas = temporadas;
    }
}
