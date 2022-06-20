package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;
import ftn.projekat.dostava.entity.Uloga;

public class DodajMenadzeraDostavljacaDto {
    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String lozinka;

    private Uloga uloga;

    private Pol pol;

    public DodajMenadzeraDostavljacaDto() { }

    public DodajMenadzeraDostavljacaDto(String ime, String prezime,
                    String korisnickoIme, String lozinka, Uloga uloga,
                    Pol pol) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
        this.pol = pol;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public Pol getPol() {
        return pol;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }
}
