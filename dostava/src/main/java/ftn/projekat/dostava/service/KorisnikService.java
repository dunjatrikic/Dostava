
package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Korisnik;
import ftn.projekat.dostava.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }
    public Korisnik login(String korisnickoime, String lozinka) //sluzi za proveru da li korisnik postoji i da li se sifra poklapa sa prosledjenom sifrom
    {
        Korisnik korisnik = korisnikRepository.getByKorisnickoIme(korisnickoime);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
        {
            return null;
        }
        return korisnik;
    }
}

