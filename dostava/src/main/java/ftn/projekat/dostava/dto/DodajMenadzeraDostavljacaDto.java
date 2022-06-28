package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Pol;
import ftn.projekat.dostava.entity.Uloga;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DodajMenadzeraDostavljacaDto {
    private static String ime;

    private static String prezime;

    private static String korisnickoIme;

    private static String lozinka;

    private static Uloga uloga;

    private static Pol pol;

    private static LocalDate datumRodjenja;

    public DodajMenadzeraDostavljacaDto() { }

    public DodajMenadzeraDostavljacaDto(String ime, String prezime,
                    String korisnickoIme, String lozinka, String uloga,
                    String pol,String datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = Uloga.valueOf(uloga);
        this.pol = Pol.valueOf(pol);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(datumRodjenja, formatter);
        this.datumRodjenja = datum;
    }

    public static String getIme() {
        return ime;
    }

    public static String getPrezime(){
        return prezime;
    }

    public static String getKorisnickoIme() {
        return korisnickoIme;
    }

    public static String getLozinka() {
        return lozinka;
    }

    public static Uloga getUloga() {
        return uloga;
    }

    public static Pol getPol() {
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

    public static LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public static void setDatumRodjenja(LocalDate datumRodjenja) {
        DodajMenadzeraDostavljacaDto.datumRodjenja = datumRodjenja;
    }
}
