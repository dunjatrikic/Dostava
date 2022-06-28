package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Menadzer;

public class RestoranDto {

    private String nazivRestorana;

    private String tipRestorana;

    private Long idLokacije;

    private String menadzer;

    public RestoranDto() { }
    public RestoranDto(String nazivRestorana, String tipRestorana, Long idLokacije,String menadzer) {
        this.nazivRestorana = nazivRestorana;
        this.tipRestorana = tipRestorana;
        this.idLokacije = idLokacije;
        this.menadzer = menadzer;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public long getIdLokacije() {
        return idLokacije;
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacijaRestorana(long lokacijaRestorana) {
        this.idLokacije = lokacijaRestorana;
    }
}
