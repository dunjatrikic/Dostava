package ftn.projekat.dostava.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "porudzbina_id",referencedColumnName = "id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
    }
    public void dodajPorudzbinu(Porudzbina p){
        porudzbine.add(p);
    }
    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, LocalDate datumRodjenja) {
        super(korisnickoIme,lozinka,ime,prezime,pol,datumRodjenja);
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
