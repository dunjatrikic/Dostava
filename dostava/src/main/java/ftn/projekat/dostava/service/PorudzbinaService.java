package ftn.projekat.dostava.service;


import ftn.projekat.dostava.entity.Kupac;
import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.entity.StatusPorudzbine;
import ftn.projekat.dostava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<Porudzbina> findAllByRestoran(Restoran restoran)
    {
        return porudzbinaRepository.findAllByRestoran(restoran);
    }
    public List<Porudzbina> findAll()
    {
        return porudzbinaRepository.findAll();
    }
    public List<Porudzbina> findAllbyKupac(Kupac kupac)
    {
        return porudzbinaRepository.findAllByKupac(kupac);
    }
    public Porudzbina save(Porudzbina porudzbina)
    {
        return porudzbinaRepository.save(porudzbina);
    }

    public List<Porudzbina> findAllByStatus(StatusPorudzbine status)
    {
        return porudzbinaRepository.findAllByStatus(status);
    }

    public Porudzbina findFirstbyStatus(StatusPorudzbine status, Long id)
    {
        return porudzbinaRepository.findByStatusAndKupacId(status, id);
    }

}
