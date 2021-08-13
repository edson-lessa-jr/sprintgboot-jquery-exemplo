package br.unisul.aula.dto;

public class TemporadaDTO {

    private long id;
    private int numero;
    private String descricao;

    public TemporadaDTO(long id, int numero, String descricao) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
    }

    public TemporadaDTO(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public TemporadaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
