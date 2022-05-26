package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private String tipRestorana;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "artikal_id",referencedColumnName = "id")
    private Set<Artikal>artikli = new HashSet<>();

    @OneToOne
    private Lokacija lokacija;

    public Restoran() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return "Restoran{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tipRestorana='" + tipRestorana + '\'' +
                ", artikli=" + artikli +
                ", lokacija=" + lokacija +
                '}';
    }
}
