package ftn.projekat.dostava.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private java.util.UUID UUID;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Dostavljac dostavljac;

    @ManyToOne
    private Kupac kupac;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PoruceniArtikli> artikli = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restorana", referencedColumnName = "id")
    private Restoran restoran;


    @JsonIgnore
    @OneToMany(mappedBy = "porudzbina",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "uuid_porudzbine",referencedColumnName = "uuid")
    private Set<StavkaPorudzbine> stavkaPorudzbine = new HashSet<>();
    @Column
    private double ukCena;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme = new Date();

    @Column
    private String kupacIme;

    @Column
    private StatusPorudzbine status;

    public double izracunajCenu() {
        ukCena = 0;
        for(StavkaPorudzbine s: stavkaPorudzbine)
        {
            ukCena += (s.getPorucenaKolicina() * s.getArtikal().getCena());
        }
        return ukCena;
    }

    public void dodajStavku(StavkaPorudzbine novaStavka)
    {
        this.stavkaPorudzbine.add(novaStavka);
    }

    public void ukloniStavku(StavkaPorudzbine izbrisiStavku)
    {
        this.stavkaPorudzbine.remove(izbrisiStavku);
    }

    public Porudzbina() {
    }


    public Long getId() {
        return id;
    }

    public void setUUID(Long id) {
        this.id = id;
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
        return restoran;
    }

    public void setRestoranporuceno(Restoran restoran) {
        this.restoran = restoran;
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

    public StatusPorudzbine getStatus() {
        return status;
    }


    public void setStatus(StatusPorudzbine status) {
        this.status = status;
    }

    public Set<StavkaPorudzbine> getStavkaPorudzbine() {
        return stavkaPorudzbine;
    }

    public void setStavkaPorudzbine(Set<StavkaPorudzbine> stavkaPorudzbine) {
        this.stavkaPorudzbine = stavkaPorudzbine;
    }


}