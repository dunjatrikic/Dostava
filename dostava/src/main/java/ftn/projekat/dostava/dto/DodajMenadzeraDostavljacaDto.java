package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;
import ftn.projekat.dostava.entity.Uloga;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DodajMenadzeraDostavljacaDto {
    private  String ime;

    private String prezime;

    private String korisnickoIme;

    private  String lozinka;

    private String uloga;

    private  String pol;

    private  String datumRodjenja;

    public DodajMenadzeraDostavljacaDto() { }

    public DodajMenadzeraDostavljacaDto(String ime, String prezime,
                    String korisnickoIme, String lozinka, String uloga,
                    String pol,String datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public  String getIme() {
        return ime;
    }

    public String getPrezime(){
        return prezime;
    }

    public  String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public  String getUloga() {
        return uloga;
    }

    public  String getPol() {
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

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public  String getDatumRodjenja() {
        return datumRodjenja;
    }

    public  void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
