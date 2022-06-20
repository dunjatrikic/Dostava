package ftn.projekat.dostava.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private java.util.UUID UUID;
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/


   @ManyToOne
   private Dostavljac dostavljac;

   @ManyToOne
   private Kupac kupac;

   @OneToMany(mappedBy = "porudzbina", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Set<PoruceniArtikli> artikli = new HashSet<>();

   @OneToOne
   private Restoran restoranporuceno;

    @Column
    private double ukCena;

    @Column
    private Date datumVreme;

    @Column
    private String kupacIme;

    @Column
    private Status status;

    public Porudzbina() {
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<PoruceniArtikli> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<PoruceniArtikli> artikli) {
        this.artikli = artikli;
    }

    public Restoran getRestoranporuceno() {
        return restoranporuceno;
    }

    public void setRestoranporuceno(Restoran restoranporuceno) {
        this.restoranporuceno = restoranporuceno;
    }

    public double getUkCena() {
        return ukCena;
    }

    public void setUkCena(double ukCena) {
        this.ukCena = ukCena;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getKupacIme() {
        return kupacIme;
    }

    public void setKupacIme(String kupacIme) {
        this.kupacIme = kupacIme;
    }

    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }
}
