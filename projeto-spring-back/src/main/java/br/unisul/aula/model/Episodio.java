package br.unisul.aula.model;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "tb_episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "temporada_id")
    private Temporada temporada;

    public Episodio(String nome, int numero, Temporada temporada) {
        this.nome = nome;
        this.numero = numero;
        this.temporada = temporada;
    }

    public Episodio(long id, String nome, int numero, Temporada temporada) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.temporada = temporada;
    }

    public Episodio() {
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episodio)) return false;

        Episodio episodio = (Episodio) o;

        return getId() == episodio.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
    public void copiaAtributos(Episodio episodio){
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
                        atualAtributosAntigo.set(this, atualAtributosNovo.get(episodio));
                    } catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
