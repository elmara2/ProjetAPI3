package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    public List<Enseignant> findEnseignantsByNomLike(String nom);
    public Enseignant findEnseignantByMatriculeLike(String matricule);
    public Enseignant findEnseignantByIdenseignant(Integer id);
    public void deleteEnseignantByIdenseignant(Integer id);
}
