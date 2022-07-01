package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Komentar;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KomentarService {
    @Autowired
    private KomentarRepository komentarRepository;

    public Komentar save(Komentar komentar) { return komentarRepository.save(komentar); }

    public List<Komentar> findAll() { return komentarRepository.findAll(); }

    public List<Komentar> findAll(Restoran restoran) {
        List<Komentar> listaKomentara = new ArrayList<>();

        for(Komentar k : this.findAll()){
            if(k.getRestoran() == restoran){
                listaKomentara.add(k);
            }
        }
        return listaKomentara;
    }

    public double prosecnaOcena(List<Komentar> listaKomentara){
        double rezultat = 0;
        for(Komentar k : listaKomentara){
            rezultat += k.getOcena();
        }

        return rezultat / listaKomentara.size();
    }


}
