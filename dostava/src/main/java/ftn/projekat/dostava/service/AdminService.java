package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.*;
import ftn.projekat.dostava.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    public AdminRepository adminRepository;

    @Autowired
    public  MenadzerService menadzerService;

    @Autowired
    public DostavljacService dostavljacService;

    @Autowired
    public  RestoranService restoranService;

    @Autowired
    public KorisnikService korisnikService;

    @Autowired
    public  LokacijaService lokacijaService;

    public Menadzer saveMenadzer(Menadzer noviMenadzer){
        return this.menadzerService.save(noviMenadzer);
    }

    public Dostavljac saveDostavljac(Dostavljac dostavljac){
        return this.dostavljacService.save(dostavljac);
    }

    public Restoran saveRestoran(Restoran noviRestoran){
        return this.restoranService.save(noviRestoran);
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Korisnik getByKorisnickoIme(String userName){
        return  this.korisnikService.findByKorisnickoIme(userName);
    }

    public Korisnik getByPrezime(String prezime){
        return this.korisnikService.findByPrezime(prezime);}

    public Restoran getByLokacija(Lokacija lokacija){
        return  this.restoranService.findOneByLokacija(lokacija);
    }

    public Korisnik  getByIme(String ime){ return this.korisnikService.findByIme(ime);}
    public Lokacija getLokacijaById(Long id){
        return  this.lokacijaService.getLokacijaById(id);
    }

    public Restoran getRestoranByNaziv(String naziv){
        return  this.restoranService.findByNaziv(naziv);
    }

}
