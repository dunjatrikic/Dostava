package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca,Long> {
}
