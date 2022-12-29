package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfEnseignantService extends InterfService<Enseignant>{
    public List<Enseignant> read(String nom);
    public Enseignant rechEnseignant(String matricule);
    public Salle rechSalleDeEnseignant(Enseignant enseignant) throws Exception;
    public Enseignant modifSalleDeEnseignant(Enseignant enseignant,Salle salle) throws Exception;
    public List<Infos> readInfos(Enseignant enseignant) throws Exception;
}
