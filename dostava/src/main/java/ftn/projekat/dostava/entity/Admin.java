package ftn.projekat.dostava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Admin extends Korisnik{


    public Admin() {
        super();
        super.setUloga(Uloga.Admin);
    }

    public Admin(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, LocalDate datumRodjenja) {
        super(korisnickoIme,lozinka,ime,prezime,pol,datumRodjenja);
        setUloga(Uloga.Admin);
    }
}