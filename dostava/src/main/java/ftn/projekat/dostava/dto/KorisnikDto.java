package ftn.projekat.dostava.dto;

public class KorisnikDto {

    private String korisnickoIme;
    private String ime;
    private String prezime;

    private String lozinka;

    public KorisnikDto() {
    }

    public KorisnikDto(String korisnickoIme, String ime, String prezime, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
    }

    public KorisnikDto(KorisnikDto korisnik){
        this.korisnickoIme = korisnik.getKorisnickoIme();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.lozinka = korisnik.getLozinka();

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
}
