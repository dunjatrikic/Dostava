package ftn.projekat.dostava.repository;

import ftn.projekat.dostava.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
