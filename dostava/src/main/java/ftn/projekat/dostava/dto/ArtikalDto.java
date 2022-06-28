package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.TipArtikla;

public class ArtikalDto {

    private String naziv;

    private float cena;

    private String tipArtikla;

    private String opis;

    private int kolicina;

    public ArtikalDto() {
    }

    public ArtikalDto(String naziv, float cena, String tipArtikla) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.opis = "";
        this.kolicina = 0;
    }

    public ArtikalDto(String naziv, float cena, String tipArtikla, String opis, int kolicina) {
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

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
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

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
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
