package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
}
