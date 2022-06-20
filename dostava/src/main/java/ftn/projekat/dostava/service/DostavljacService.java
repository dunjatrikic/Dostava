package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Dostavljac;
import ftn.projekat.dostava.repository.DostavljacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DostavljacService {
    @Autowired
    public DostavljacRepository dostavljacRepository;

    public Dostavljac save(Dostavljac dostavljac){
        return this.dostavljacRepository.save(dostavljac);
    }

    public List<Dostavljac> findAll(){
        return dostavljacRepository.findAll();
    }
}
