package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.repository.DostavljacRepository;
import ftn.projekat.dostava.repository.KorisnikRepository;
import ftn.projekat.dostava.repository.KupacRepository;
import ftn.projekat.dostava.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik,Uloga uloga){
            if(uloga.equals(Uloga.Menadzer)) {
                menadzerRepository.save((Menadzer) korisnik);
            }else if(uloga.equals(Uloga.Kupac)){
                kupacRepository.save((Kupac) korisnik);
            }else if (uloga.equals(Uloga.Dostavljac)){
                dostavljacRepository.save((Dostavljac) korisnik);
            }
            return korisnikRepository.save(korisnik);
        }

    public Korisnik login(String korisnickoime, String lozinka) //sluzi za proveru da li korisnik postoji i da li se sifra poklapa sa prosledjenom sifrom
    {
        Korisnik korisnik = korisnikRepository.getBykorisnickoIme(korisnickoime);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
        {
            return null;
        }
        return korisnik;
    }

    public List<Korisnik> findAllByUlogaOrderById(Uloga uloga) {
        return korisnikRepository.findAllByUlogaOrderById(uloga);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }


    public Korisnik findByKorisnickoIme(String userName){
        Korisnik korisnik = korisnikRepository.getBykorisnickoIme(userName);

        if(korisnik == null){
            return null;
        }
        return korisnik;
    }

    public Korisnik findByPrezime(String prezime) {return korisnikRepository.findByPrezime(prezime);
    }

    public Korisnik findByIme(String ime) {return korisnikRepository.findByIme(ime);
    }
}
