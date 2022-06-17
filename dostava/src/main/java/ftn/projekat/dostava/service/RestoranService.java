package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Artikal;
import ftn.projekat.dostava.entity.Komentar;
import ftn.projekat.dostava.entity.Lokacija;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private  ArtikalService artikalService;

    public Restoran save(Restoran restoran){
        return this.restoranRepository.save(restoran);
    }

    //pronalazenje svih restorana
    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    //pronalazenje restorana po nazivu
    public Restoran findByNaziv(String naziv){
        Optional<Restoran> r = restoranRepository.findByNaziv(naziv);

        if(r.isPresent()){
            return r.get();
        }
        return null;
    }

    //pronalazenje  restorana po odredjenoj lokaciji
    public Restoran findOneByLokacija(Lokacija lokacija) {
        Optional<Restoran> r = restoranRepository.findByLokacija(lokacija);

        if(r.isPresent()){
            return r.get();
        }

        return null;
    }

    //pronalazenje restorana po odredjenom id-u
    public Restoran findById(long idRestorana){
        Optional<Restoran> r = restoranRepository.findById(idRestorana);

        if(r.isPresent()){
            return r.get();
        }
        return null;
    }

    public Artikal findArtikalById(long idArtikla){
        return this.artikalService.findById(idArtikla);
    }

    //pronalazenje restorana po odredjenom tipu
    public Restoran findOneByTipRestorana(String tip){
        Optional<Restoran> r = restoranRepository.findByTipRestorana(tip);

        if(r.isPresent()){
            return  r.get();
        }
        return null;
    }


    public Restoran findOneById(long id){
        Optional<Restoran> r = restoranRepository.findById(id);

        if(r.isPresent()){
            return r.get();
        }
        return null;
    }


    public Artikal saveArtikal(Artikal artikal){  return  this.artikalService.save(artikal);  }

    public void deleteArtikal(Artikal artikal){  artikalService.delete(artikal); }




}
