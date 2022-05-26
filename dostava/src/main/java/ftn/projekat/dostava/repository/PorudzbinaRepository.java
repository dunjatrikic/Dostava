package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina,Long> {
}
