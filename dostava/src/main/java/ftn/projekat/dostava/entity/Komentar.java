package ftn.projekat.dostava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "kupac_id",referencedColumnName = "id")
    private Kupac kupac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restoran_id",referencedColumnName = "id")
    private Restoran restoran;

    @Column
    private String tekst;

    @Column
    private int ocena;

    public Komentar() {
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", restoran=" + restoran +
                ", tekst='" + tekst + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}
