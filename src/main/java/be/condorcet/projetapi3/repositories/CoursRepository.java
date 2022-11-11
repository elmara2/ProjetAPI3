package be.condorcet.projetapi3.repositories;

import be.condorcet.projetapi3.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Integer>{

}
