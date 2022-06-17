package ftn.projekat.dostava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Kupac extends Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "kupac",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    @Column
    private int bodovi;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "tipKupca",referencedColumnName = "id")
    private TipKupca tip;

    @OneToMany(mappedBy = "kupac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @OneToOne(mappedBy = "kupac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Korpa korpa;


    public Kupac() {
    }

   /* public Kupac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Set<Porudzbina> porudzbine, int bodovi, TipKupca tip) {
        super(id, korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.porudzbine = porudzbine;
        this.bodovi = bodovi;
        this.uloga = Uloga.Kupac;
        this.tip = tip;
    }*/

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
    public void setId(Long id) {
        this.id = id;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    public void dodajBodove(int bodovi)
    {
        this.bodovi += bodovi;
    }
}
