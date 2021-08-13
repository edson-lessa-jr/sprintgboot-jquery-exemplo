package br.unisul.aula.model;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "tb_temporadas")
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;

    @Column(nullable = false)
    private int numero;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;


    public Temporada() {
    }

    public Temporada(int numero, String descricao, Serie serie) {
        this.numero = numero;
        this.descricao = descricao;
        this.serie = serie;
    }

    public Temporada(long id, int numero, String descricao, Serie serie) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.serie = serie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Temporada)) return false;

        Temporada temporada = (Temporada) o;

        return getId() == temporada.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
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

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void copiaAtributos(Temporada temporada){
        Field[] fieldsFromSerieAntiga = this.getClass().getDeclaredFields();
        Field[] fieldsFromSerieNova = this.getClass().getDeclaredFields();

        for (Field atualAtributosAntigo: fieldsFromSerieAntiga){
            for (Field atualAtributosNovo: fieldsFromSerieNova){
                String nomeAntigo = atualAtributosAntigo.getName();
                String nomeNovo = atualAtributosNovo.getName();

                if(nomeAntigo.equals(nomeNovo)){
                    atualAtributosAntigo.setAccessible(true);
                    atualAtributosNovo.setAccessible(true);
                    try {
                        atualAtributosAntigo.set(this, atualAtributosNovo.get(temporada));
                    } catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
