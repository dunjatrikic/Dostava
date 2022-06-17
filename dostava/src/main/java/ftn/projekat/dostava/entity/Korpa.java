package ftn.projekat.dostava.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Korpa {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kupac kupac;

    @OneToMany(mappedBy = "korpa", fetch = FetchType.EAGER)
    private Set<Artikal> artikli = new HashSet<>();

    @Column
    private double ukupnaCena;

    public Korpa() {
    }

    public Korpa(Kupac kupac, Set<Artikal> artikli) {
        this.kupac = kupac;
        this.artikli = artikli;
    }

    public Korpa(Kupac kupac, Set<Artikal> artikli, double ukupnaCena) {
        this.kupac = kupac;
        this.artikli = artikli;
        this.ukupnaCena = ukupnaCena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
