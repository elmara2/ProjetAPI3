package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle,Integer> {
}
