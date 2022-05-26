package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "kupac",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private int bodovi;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "tipKupca",referencedColumnName = "id")
    private TipKupca tip;

    public Kupac() {
        this.bodovi = 0;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        setUloga(Uloga.Kupac);

    }
    public Long getId() {
        return id;
    }


    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public TipKupca getTip() {
        return tip;
    }

    public void setTip(TipKupca tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol='" + pol + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga=" + uloga +
                ", id=" + id +
                ", porudzbine=" + porudzbine +
                ", bodovi=" + bodovi +
                ", tip=" + tip +
                '}';
    }
}
