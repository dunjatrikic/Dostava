package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac,Long> {
}
