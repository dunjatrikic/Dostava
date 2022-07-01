package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenadzerService {
    @Autowired
    public MenadzerRepository menadzerRepository;

    @Autowired
    public RestoranService restoranService;

    public Menadzer save(Menadzer menadzer){
        return this.menadzerRepository.save(menadzer);
    }

    public List<Menadzer> findAll(){
        return menadzerRepository.findAll();
    }

    public Menadzer findByKorisnickoIme(String korisnickoIme){
        Optional<Menadzer> men = menadzerRepository.findByKorisnickoIme(korisnickoIme);

        if(men.isPresent()){
            return men.get();
        }
        return null;
    }
    public Menadzer findByRestoran(Restoran restoran){
        Optional<Menadzer> men = menadzerRepository.findByRestoran(restoran);

        if(men.isPresent()){
            return men.get();
        }
        return null;
    }

    public Restoran findRestoranById(long idRestorana){
        return this.restoranService.findById(idRestorana);
    }
}
