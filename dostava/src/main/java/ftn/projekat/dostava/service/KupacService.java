package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Kupac;
import ftn.projekat.dostava.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    public Kupac registracija(String korisnickoIme,String ime,String prezime,String lozinka){
        Kupac kupac = kupacRepository.getByKorisnickoIme(korisnickoIme);
        Kupac kupac1 = new Kupac(korisnickoIme,ime,prezime,lozinka);
        if(kupac.getKorisnickoIme() == kupac1.getKorisnickoIme()){
            return null;
        }

        return kupacRepository.save(kupac1);
    }


}
