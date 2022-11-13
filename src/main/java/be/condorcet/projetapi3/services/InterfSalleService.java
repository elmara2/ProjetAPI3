package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfSalleService extends InterfService<Salle>{
    public List<Salle> read(int capacite);
    public Salle rechSalle(String sigle);
}
