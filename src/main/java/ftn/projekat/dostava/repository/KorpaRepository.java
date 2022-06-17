package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa,Long> {
}
