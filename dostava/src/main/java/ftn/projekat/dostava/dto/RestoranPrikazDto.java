package ftn.projekat.dostava.dto;

;
import ftn.projekat.dostava.entity.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestoranPrikazDto {

    private String nazivRestorana;

    private String tipRestorana;

    private StatusRestorana statusRestorana;

    private Set<ArtikalPrikazDto> artikliUPonudi = new HashSet<>();

    private Lokacija lokacija;


    private double prosek;

    private List<Komentar> komentari = new ArrayList<>();

    public RestoranPrikazDto() {
    }

    public RestoranPrikazDto(Restoran restoran) {
        this.nazivRestorana = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.lokacija = restoran.getLokacija();
        this.statusRestorana = StatusRestorana.Radi;
        double prosek = 0;
        for(Komentar k : komentari){
            prosek += k.getOcena();
        }
        this.prosek = prosek / komentari.size();
        this.komentari = new ArrayList<>();
    }


    public RestoranPrikazDto(Restoran restoran, List<Komentar> komentari) {
        this.nazivRestorana = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        for(Artikal artikal : restoran.getArtikli()){
            ArtikalPrikazDto prikazDto = new ArtikalPrikazDto(artikal);
            artikliUPonudi.add(prikazDto);
        }
        this.lokacija = restoran.getLokacija();
        this.statusRestorana = StatusRestorana.Radi;
        double prosek = 0;
        for(Komentar k : komentari){
            prosek += k.getOcena();
        }
        this.prosek = prosek / komentari.size();
        this.komentari = komentari;
    }


    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }



    public StatusRestorana getStatusRestorana() {
        return statusRestorana;
    }

    public Set<ArtikalPrikazDto> getArtikliUPonudi() {
        return artikliUPonudi;
    }

    public void setArtikliUPonudi(Set<ArtikalPrikazDto> artikliUPonudi) {
        this.artikliUPonudi = artikliUPonudi;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public double getProsek() {
        return prosek;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacijaRestorana(Lokacija lokacijaRestorana) {
        this.lokacija = lokacijaRestorana;
    }

    public void setStatusRestorana(StatusRestorana statusRestorana) {
        this.statusRestorana = statusRestorana;
    }
}
