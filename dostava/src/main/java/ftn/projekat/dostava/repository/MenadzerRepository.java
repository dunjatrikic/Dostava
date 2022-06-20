package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Menadzer;
import ftn.projekat.dostava.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer,Long> {
    List<Menadzer> findAll();
    Optional<Menadzer> findByKorisnickoIme(String korisnickoIme);

    Optional<Menadzer> findByRestoran(Restoran restoran);
}
