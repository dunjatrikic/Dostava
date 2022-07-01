package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Komentar;

public class KomentarPrikazDto {

    private String korisnickoIme;

    private String tekst;

    private int ocena;

    public KomentarPrikazDto() { }

    public KomentarPrikazDto(Komentar komentar){
        this.korisnickoIme = komentar.getKupac().getKorisnickoIme();
        this.tekst = komentar.getTekst();
        this.ocena = komentar.getOcena();
    }

    //getters
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getTextKomentara() {
        return tekst;
    }

    public int getOcena() {
        return ocena;
    }

    //setters

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setTextKomentara(String textKomentara) {
        this.tekst = textKomentara;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

}
