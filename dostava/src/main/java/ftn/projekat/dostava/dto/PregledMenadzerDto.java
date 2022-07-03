package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.entity.Restoran;

import java.util.ArrayList;
import java.util.List;

public class PregledMenadzerDto {
    private Restoran restoran;
    private List<Porudzbina> porudzbineRestorana = new ArrayList<>();

    public PregledMenadzerDto(){}

    public PregledMenadzerDto(Restoran restoran, List<Porudzbina> porudzbineRestorana) {
        this.restoran = restoran;
        this.porudzbineRestorana = porudzbineRestorana;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public List<Porudzbina> getPorudzbineRestorana() {
        return porudzbineRestorana;
    }

    public void setPorudzbineRestorana(List<Porudzbina> porudzbineRestorana) {
        this.porudzbineRestorana = porudzbineRestorana;
    }
}
