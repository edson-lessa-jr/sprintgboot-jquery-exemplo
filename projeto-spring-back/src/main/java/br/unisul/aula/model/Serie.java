package br.unisul.aula.model;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "tb_series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "ano_criacao", nullable = false)
    private int anoCriacao;

    @Column
    private String descricao;


    public Serie() {
    }

    public Serie(String nome, int anoCriacao, String descricao) {
        this.nome = nome;
        this.anoCriacao = anoCriacao;
        this.descricao = descricao;
    }

    public Serie(long id, String nome, int anoCriacao, String descricao) {
        this.id = id;
        this.nome = nome;
        this.anoCriacao = anoCriacao;
        this.descricao = descricao;
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

    public int getAnoCriacao() {
        return anoCriacao;
    }

    public void setAnoCriacao(int anoCriacao) {
        this.anoCriacao = anoCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void copiaAtributos(Serie serie){
        Field[] fieldsFromSerieAntiga = this.getClass().getDeclaredFields();
        Field[] fieldsFromSerieNova = serie.getClass().getDeclaredFields();

        for (Field atualAtributosAntigo: fieldsFromSerieAntiga){
            for (Field atualAtributosNovo: fieldsFromSerieNova){
                String nomeAntigo = atualAtributosAntigo.getName();
                String nomeNovo = atualAtributosNovo.getName();

                if(nomeAntigo.equals(nomeNovo)){
                    atualAtributosAntigo.setAccessible(true);
                    atualAtributosNovo.setAccessible(true);
                    try {
                        atualAtributosAntigo.set(this, atualAtributosNovo.get(serie));
                    } catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;

        Serie serie = (Serie) o;

        return getId() == serie.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }


}
