package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar,Long> {
}
