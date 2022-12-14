package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle,Integer> {
    public List<Salle> findSallesByCapacite(Integer capacite);
    public Salle findSalleBySigleLike(String sigle);
    public List<Salle> findAllByCapaciteAfter(Integer capacite);
}
