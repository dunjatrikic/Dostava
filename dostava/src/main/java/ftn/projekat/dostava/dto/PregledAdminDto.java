package ftn.projekat.dostava.dto;

import ftn.projekat.dostava.entity.Admin;
import ftn.projekat.dostava.entity.Dostavljac;
import ftn.projekat.dostava.entity.Kupac;
import ftn.projekat.dostava.entity.Menadzer;

import java.util.ArrayList;
import java.util.List;

public class PregledAdminDto {
    private List<Menadzer> listaMenadzera = new ArrayList<>();
    private List<Dostavljac> listaDostavljaca = new ArrayList<>();
    private List<Kupac> listaKupaca = new ArrayList<>();
    private List<Admin> listaAdmina = new ArrayList<>();

    public PregledAdminDto(){}

    public PregledAdminDto(List<Menadzer> listaMenadzera, List<Dostavljac> listaDostavljaca, List<Kupac> listaKupaca, List<Admin> listaAdmina) {
        this.listaMenadzera = listaMenadzera;
        this.listaDostavljaca = listaDostavljaca;
        this.listaKupaca = listaKupaca;
        this.listaAdmina = listaAdmina;
    }

    public List<Menadzer> getListaMenadzera() {
        return listaMenadzera;
    }

    public void setListaMenadzera(List<Menadzer> listaMenadzera) {
        this.listaMenadzera = listaMenadzera;
    }

    public List<Dostavljac> getListaDostavljaca() {
        return listaDostavljaca;
    }

    public void setListaDostavljaca(List<Dostavljac> listaDostavljaca) {
        this.listaDostavljaca = listaDostavljaca;
    }

    public List<Kupac> getListaKupaca() {
        return listaKupaca;
    }

    public void setListaKupaca(List<Kupac> listaKupaca) {
        this.listaKupaca = listaKupaca;
    }

    public List<Admin> getListaAdmina() {
        return listaAdmina;
    }

    public void setListaAdmina(List<Admin> listaAdmina) {
        this.listaAdmina = listaAdmina;
    }


}
