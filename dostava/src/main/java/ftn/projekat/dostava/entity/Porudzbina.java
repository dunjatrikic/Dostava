package ftn.projekat.dostava.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

enum Status {
    Obrada,
    U_pripremi,
    Ceka_dostavljaca,
    U_transportu,
    Dostavljena,
    Otkazana;

}

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private java.util.UUID UUID;
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @ManyToMany
    @JoinTable(name = "sadrzi", joinColumns = @JoinColumn(name = "porudzbina_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "artikal_id",referencedColumnName = "id"))
    private Set<Artikal> artikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Restoran restoran;

    @Column
    private float ukCena;

    @Column
    private Date datum;

    @Column
    private Time vreme;

    @Column
    @Enumerated
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kupac kupac;

    public Porudzbina() {
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public float getUkCena() {
        return ukCena;
    }

    public void setUkCena(float ukCena) {
        this.ukCena = ukCena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "ID=" + UUID +
                ", artikli=" + artikli +
                ", restoran=" + restoran +
                ", ukCena=" + ukCena +
                ", datum=" + datum +
                ", vreme=" + vreme +
                ", status=" + status +
                ", kupac=" + kupac +
                '}';
    }
}
