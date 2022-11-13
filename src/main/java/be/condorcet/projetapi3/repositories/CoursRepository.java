package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Integer>{
    public List<Cours> findCoursByIntituleLike(String intitule);
    public Cours findCoursByCodeLike(String code);
    public Cours findCoursByIdcours(Integer idcours);
    public void deleteCoursByIdcours(Integer idcours);
}
