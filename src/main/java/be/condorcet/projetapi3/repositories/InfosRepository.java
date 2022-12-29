package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface InfosRepository extends JpaRepository<Infos, InfosPK> {
    public List<Infos> findInfosByNbreheuresLike(Integer nbreheures);
    public List<Infos> findInfosBySalle(Salle salle);
    public List<Infos> findInfosByClasse(Classe classe);
    public List<Infos> findInfosByCours(Cours cours);
}
