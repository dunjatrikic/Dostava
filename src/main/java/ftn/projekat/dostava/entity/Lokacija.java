package ftn.projekat.dostava.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@JsonIgnoreProperties(value ={"hibernateLazyInitializer","handler"})
@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Restoran restoran;

    @Column
    private double geografskaDuzina; // bolje double

    @Column
    private double geografskaSirina;

    @Column
    private String adresa;

    public Lokacija() {
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

    public void setGeografskaDuzina(float geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGeografskaSirina(float geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

}
