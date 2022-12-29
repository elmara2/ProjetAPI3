package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Integer>{
    public List<Cours> findCoursByIntituleLike(String intitule);
    public Cours findCoursByCodeLike(String code);
}
