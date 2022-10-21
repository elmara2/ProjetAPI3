package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe,Integer> {
    public Classe findClasseByIdclasseLike(int idclasse);
    public void deleteClasseBySigleLike(String sigle);
}
