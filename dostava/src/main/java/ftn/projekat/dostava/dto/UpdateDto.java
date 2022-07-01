package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateDto {

    String korisnickoIme;
    String ime;
    String prezime;
    String lozinka;
    Pol pol;
    LocalDate datumRodjenja;

    public UpdateDto(String korisnickoIme, String ime, String prezime, String lozinka, String polKorisnika, String datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        Pol pol = Pol.valueOf(polKorisnika);
        this.pol = pol;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(datumRodjenja, formatter);
        this.datumRodjenja = datum;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
