package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfSalleService extends InterfService<Salle>{
    public List<Salle> read(int capacite);

    List<Salle> readByCapacitySupp(int capacite) throws Exception;

    public Salle rechSalle(String sigle);
    public List<Enseignant> listEnseignant(Salle salle) throws Exception;
    public void ajoutEnseignant(Enseignant enseignant,Salle salle) throws Exception;
    public void suppEnseignant(Enseignant enseignant,Salle salle) throws Exception;
    public List<Infos> readInfos(Salle salle) throws Exception;
}
