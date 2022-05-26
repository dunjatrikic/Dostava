package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float geografskaDuzina; // bolje double

    @Column
    private float geografskaSirina;

    @Column
    private String adresa;

    public Lokacija() {
    }

    public float getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public float getGeografskaSirina() {
        return geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeografskaDuzina(float geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGeografskaSirina(float geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Lokacija{" +
                "id=" + id +
                ", geografskaDuzina=" + geografskaDuzina +
                ", geografskaSirina=" + geografskaSirina +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
