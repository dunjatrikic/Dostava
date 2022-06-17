package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.PoruceniArtikli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoruceniArtikliRepository extends JpaRepository<PoruceniArtikli, Long> {

}
