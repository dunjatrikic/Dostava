package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Kupac;
import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.entity.StatusPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina,Long> {
    List<Porudzbina> findAllByKupac(Kupac kupac);
    List<Porudzbina> findAll();
    List<Porudzbina> findAllByStatus(StatusPorudzbine status);
    Porudzbina findByStatusAndKupacId(StatusPorudzbine status, long id);

    List<Porudzbina> findAllByRestoran(Restoran restoran);

}
