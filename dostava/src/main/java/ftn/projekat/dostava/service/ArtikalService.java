package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Artikal;
import ftn.projekat.dostava.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ArtikalService {
    @Autowired
    private ArtikalRepository artikalRepository;


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
}
