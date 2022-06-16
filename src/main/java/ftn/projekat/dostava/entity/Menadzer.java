package ftn.projekat.dostava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Menadzer extends Korisnik implements Serializable {


    @OneToOne(mappedBy = "menadzer")
    @JsonIgnore
    private Restoran restoran;

    @OneToOne
    @JoinColumn(name = "restoran",referencedColumnName = "id")
    private Restoran zaduzenRestoran;


    public Menadzer(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        super(id, korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
        this.uloga = Uloga.Menadzer;
    }

    public Menadzer() {
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        setUloga(Uloga.Menadzer);


    }

    public Restoran getZaduzenRestoran() {
        return zaduzenRestoran;
    }

    public void setZaduzenRestoran(Restoran zaduzenRestoran) {
        this.zaduzenRestoran = zaduzenRestoran;
    }
}
