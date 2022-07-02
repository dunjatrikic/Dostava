package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.TipArtikla;

public class ArtikalDto {

    private String naziv;

    private String cena;

    private String tipArtikla;

    private String opis;

    private String kolicina;

    public ArtikalDto() {
    }

    public ArtikalDto(String naziv, String cena, String tipArtikla) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.opis = "";
        this.kolicina = "";
    }

    public ArtikalDto(String naziv, String cena, String tipArtikla, String opis, String kolicina) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.opis = opis;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(String tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "ArtikalDto{" +
                "naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tipArtikla=" + tipArtikla +
                ", opis='" + opis + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }
}
