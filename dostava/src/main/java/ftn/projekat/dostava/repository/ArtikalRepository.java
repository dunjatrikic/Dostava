package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal,Long> {
}
