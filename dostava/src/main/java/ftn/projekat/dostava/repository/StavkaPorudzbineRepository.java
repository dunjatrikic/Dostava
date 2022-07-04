package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.StavkaPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine,Long> {
    StavkaPorudzbine getById(Long id);
}
