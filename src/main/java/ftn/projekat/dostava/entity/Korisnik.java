package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "korisnickoIme",unique = true,nullable = false)
    protected String korisnickoIme;

    @Column(name = "lozinka")
    protected String lozinka;

    @Column(name = "ime")
    protected String ime;

    @Column(name = "prezime")
    protected String prezime;

    @Column(name = "pol")
    protected String pol;

    @Column(name = "datumRodjenja")
    protected Date datumRodjenja;

    @Column(name = "uloga")
    protected Uloga uloga;

    public Korisnik() {
    }

    public Korisnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
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
