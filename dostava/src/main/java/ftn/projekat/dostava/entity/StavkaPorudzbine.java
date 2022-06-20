package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StavkaPorudzbine implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "artikal_id",referencedColumnName = "id")
    private Artikal artikal;

    @Column
    private int kolicina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "StavkaPorudzbine{" +
                "id=" + id +
                ", artikal=" + artikal +
                ", kolicina=" + kolicina +
                '}';
    }
}
