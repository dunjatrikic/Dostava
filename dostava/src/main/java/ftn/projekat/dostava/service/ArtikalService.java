package ftn.projekat.dostava.service;

import ftn.projekat.dostava.dto.ArtikalDto;
import ftn.projekat.dostava.entity.Artikal;
import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.entity.TipArtikla;
import ftn.projekat.dostava.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikalService {
    @Autowired
    public ArtikalRepository artikalRepository;
    @Autowired
    public RestoranService restoranService;

    @Autowired
    public MenadzerService menadzerService;



    public Artikal save(Artikal artikal){
        return this.artikalRepository.save(artikal);
    }


    public void delete(Artikal artikal) {   artikalRepository.delete(artikal);  }

    public Artikal findById(long id){
        Optional<Artikal> artikal = artikalRepository.findById(id);

        if(artikal.isPresent()){
            return artikal.get();
        }
        return null;
    }

    public List<Artikal> findAll() { return artikalRepository.findAll(); }

    public void update(Long id, ArtikalDto artikalDto, Menadzer menadzer){
        Artikal artikal = this.findById(id);

        menadzer.getZaduzenRestoran().getArtikli().remove(artikal);

        if(!artikalDto.getNaziv().isEmpty()){
            artikal.setNaziv(artikalDto.getNaziv());
        }
        if(artikalDto.getCena() > 0){
            artikal.setCena(artikalDto.getCena());
        }
        if(artikalDto.getTipArtikla() != null){
            TipArtikla tip = TipArtikla.valueOf(artikalDto.getTipArtikla());
            artikal.setTip(tip);
        }
        if(artikalDto.getKolicina() > 0){
            artikal.setKolicina(artikalDto.getKolicina());
        }
        if(!artikalDto.getOpis().isEmpty()){
            artikal.setOpis(artikalDto.getOpis());
        }

        this.artikalRepository.save(artikal);

        menadzer.getZaduzenRestoran().getArtikli().add(artikal);

        restoranService.save(menadzer.getZaduzenRestoran());

        menadzerService.save(menadzer);
    }



}
