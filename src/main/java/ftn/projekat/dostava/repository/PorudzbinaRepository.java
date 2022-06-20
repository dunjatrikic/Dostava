package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Kupac;
import ftn.projekat.dostava.entity.Porudzbina;
import ftn.projekat.dostava.entity.Restoran;
import ftn.projekat.dostava.entity.Status;
import org.hibernate.event.service.spi.EventListenerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Stack;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina,Long> {
    List<Porudzbina> findAllByKupac(Kupac kupac);
    List<Porudzbina> findAll();
    List<Porudzbina> findAllByStatus(Status status);
    Porudzbina findByStatusAndKupacId(Status status, long id);

    List<Porudzbina> findAllByRestoran(Restoran restoran);

}
