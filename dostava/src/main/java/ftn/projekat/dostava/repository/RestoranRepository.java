package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Artikal;
import ftn.projekat.dostava.entity.Lokacija;
import ftn.projekat.dostava.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran,Long> {
    Optional<Restoran> findByNaziv(String naziv);

    Optional<Restoran> findByLokacija(Lokacija lokacija);

    Optional<Restoran> findByTipRestorana(String tip);
   // Optional<Restoran> findByArtikal(Artikal artikal);

}
