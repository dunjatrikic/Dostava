package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double geografskaDuzina; // bolje double

    @Column
    private double geografskaSirina;

    @Column
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(float geografskaDuzina, float geografskaSirina, String adresa) {
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
    }

    public Long getId() {
        return id;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
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
