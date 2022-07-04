package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.StavkaPorudzbine;
import ftn.projekat.dostava.repository.StavkaPorudzbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaPorudzbineService {
    @Autowired
    private StavkaPorudzbineRepository stavkaPorudzbineRepository;

    public StavkaPorudzbine getById(Long id){
        return stavkaPorudzbineRepository.getById(id);
    }
    public StavkaPorudzbine save(StavkaPorudzbine stavka){return  stavkaPorudzbineRepository.save(stavka);}
    public void deleteById(Long id){
        stavkaPorudzbineRepository.deleteById(id);
    }
}
