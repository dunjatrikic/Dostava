package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer,Long> {
}
