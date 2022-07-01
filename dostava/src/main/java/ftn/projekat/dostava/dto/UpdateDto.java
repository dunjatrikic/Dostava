package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateDto {

   private  String korisnickoIme;
   private  String ime;
   private String prezime;
   private String lozinka;
    private String pol;
   private String datumRodjenja;

    public UpdateDto(String korisnickoIme, String ime, String prezime, String lozinka, String pol, String datum) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.pol = pol;
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

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
