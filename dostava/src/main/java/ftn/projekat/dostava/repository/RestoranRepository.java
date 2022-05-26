package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran,Long> {
}
