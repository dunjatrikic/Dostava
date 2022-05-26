package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Dostavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac,Long> {
}
