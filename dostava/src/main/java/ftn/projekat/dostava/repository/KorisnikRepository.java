package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Korisnik;
import ftn.projekat.dostava.entity.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik,Long> {
    List<Korisnik> findAllByImeOrderById(String ime);

    List<Korisnik> findAllByUlogaOrderById(Uloga uloga);

    Korisnik getBykorisnickoIme(String korisnickoIme);
}
