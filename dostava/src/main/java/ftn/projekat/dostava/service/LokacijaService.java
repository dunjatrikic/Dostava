package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Lokacija;
import ftn.projekat.dostava.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LokacijaService {
    @Autowired
    LokacijaRepository lokacijaRepository;

    public Lokacija getLokacijaById(Long id) {
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
        if (lokacija.isPresent())
            return lokacija.get();

        return null;
    }



}

