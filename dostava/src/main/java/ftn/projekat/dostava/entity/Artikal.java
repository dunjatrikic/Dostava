package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

enum Tip {
    jelo,
    pice;
}

@Entity
public class Artikal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private float cena;

    @Column
    @Enumerated
    private Tip tip;

    @Column
    private double kolicina;

    @Column
    private String opis;


    public Artikal() {
    }
    public Artikal(Long id, String naziv, float cena, Tip tip, double kolicina, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tip=" + tip +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                '}'+"\n";
    }
}
