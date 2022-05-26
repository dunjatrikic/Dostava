package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "restoran",referencedColumnName = "id")
    private Restoran restoran;

    public Menadzer() {
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        setUloga(Uloga.Menadzer);


    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Menadzer{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol='" + pol + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga=" + uloga +
                ", id=" + id +
                ", restoran=" + restoran +
                '}';
    }
}
