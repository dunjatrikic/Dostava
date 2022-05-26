package ftn.projekat.dostava.entity;

import javax.persistence.*;

@Entity
public class TipKupca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private double popust;

    @Column
    private int trazeniBodovi;

    public long getId() {
        return id;
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getTrazeniBodovi() {
        return trazeniBodovi;
    }

    public void setTrazeniBodovi(int trazeniBodovi) {
        this.trazeniBodovi = trazeniBodovi;
    }
}
