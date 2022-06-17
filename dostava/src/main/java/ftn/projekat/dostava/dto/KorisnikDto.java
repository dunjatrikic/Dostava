package ftn.projekat.dostava.dto;


import ftn.projekat.dostava.entity.Korisnik;


public class KorisnikDto {
    private Long id;

    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;



    public KorisnikDto() {
    }

    public KorisnikDto(Long id, String korisnickoIme, String lozinka, String ime, String prezime) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.lozinka = korisnik.getLozinka();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
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
}
