package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.InfosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfosRepository extends JpaRepository<Infos, InfosPK> {

}
