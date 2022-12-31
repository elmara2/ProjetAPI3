package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.InfosPK;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos>{
    public List<Infos> read(Integer nbreheures);
    public Infos modifEnseignant(Infos infos, Enseignant enseignant) throws Exception;
    public Infos modifSalle(Infos infos, Salle salle) throws Exception;
}
