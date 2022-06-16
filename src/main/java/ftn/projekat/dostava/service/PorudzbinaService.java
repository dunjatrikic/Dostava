package ftn.projekat.dostava.service;

import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.entity.Status;
import ftn.projekat.dostava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.HashSet;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private PoruceniArtikliRepository poruceniArtikliRepository;

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public Set<Porudzbina> pregledSlobodnih(Status status) {
        Set<Porudzbina> porudzbine = new HashSet<>(porudzbinaRepository.findAll());
        Set<Porudzbina> vrati = new HashSet<>();
        for (Porudzbina p : porudzbine)
        {
            if(p.getStatus()==status)
            {
                vrati.add(p);
            }
        }
        return vrati;
    }

    public Set<Porudzbina> porudzbineRestorana(Menadzer menadzer)
    {
       // Restoran restoran = menadzer.getZa
    }


}
