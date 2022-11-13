package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Enseignant;

import java.util.List;

public interface InterfEnseignantService extends InterfService<Enseignant>{
    public List<Enseignant> read(String nom);
    public Enseignant rechEnseignant(String matricule);
}
