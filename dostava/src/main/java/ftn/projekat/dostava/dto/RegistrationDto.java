package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;


public class RegistrationDto {

    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String lozinka;
    private String pol;
    private String datumRodjenja;



    public RegistrationDto(String korisnickoIme, String ime, String prezime, String lozinka, String  pol, String datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
    }

    public RegistrationDto(RegistrationDto register){
        this.korisnickoIme = register.getKorisnickoIme();
        this.ime = register.getIme();
        this.prezime = register.getPrezime();
        this.lozinka = register.getLozinka();
        this.datumRodjenja = register.getDatumRodjenja();
        this.pol = register.getPol();

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
