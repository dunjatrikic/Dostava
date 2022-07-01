package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Lokacija;
import ftn.projekat.dostava.entity.Restoran;

public class PretragaDto {

    private String naziv;

    private String tipRestorana;

    private String adresaLokacije;

    public PretragaDto(){}
    public PretragaDto(String naziv, Lokacija lokacija, String tipRestorana){
        this.naziv = naziv;
        this.adresaLokacije = lokacija.getAdresa();
        this.tipRestorana = tipRestorana;
    }
    public PretragaDto(Restoran restoran){
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.adresaLokacije = restoran.getLokacija().getAdresa();
    }

    public String getNaziv() {  return  naziv;  }
    public void setNaziv(String naziv) {  this.naziv = naziv;   }

    public String getTipRestorana() {  return  tipRestorana;  }
    public void setTipRestorana(String tip){  this.tipRestorana = tip;  }

    public String getAdresaLokacije() { return adresaLokacije;  }

    public void setAdresaLokacije(String adresaLokacije) {  this.adresaLokacije = adresaLokacije;   }
}
