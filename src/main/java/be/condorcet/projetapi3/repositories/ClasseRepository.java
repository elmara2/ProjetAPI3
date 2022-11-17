package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Integer> {
    public Classe findClasseByIdclasseLike(Integer idclasse);
    public List<Classe> findClassesBySpecialiteLike(String specialite);
    public Classe findClasseBySigleLike(String sigle);
}
