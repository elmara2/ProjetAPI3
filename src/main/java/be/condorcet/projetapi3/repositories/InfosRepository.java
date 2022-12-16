package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.InfosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfosRepository extends JpaRepository<Infos, InfosPK> {
    public List<Infos> findInfosByNbreheuresLike(Integer nbreheures);

}
