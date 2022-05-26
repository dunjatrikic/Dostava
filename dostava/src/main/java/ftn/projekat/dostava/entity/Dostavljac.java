package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "porudzbina",referencedColumnName = "id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;

        setUloga(Uloga.Dostavljac);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    @Override
    public String toString() {
        return "Dostavljac{" +
                "id=" + id +
                ", porudzbine=" + porudzbine +
                ", id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol='" + pol + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga=" + uloga +
                '}';
    }
}
