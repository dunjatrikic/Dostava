package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Restoran;

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

    public RestoranDto(Restoran restoran) {
        this.nazivRestorana = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.idLokacije= restoran.getLokacija().getId();
        this.menadzer = restoran.getMenadzer().getKorisnickoIme();
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
