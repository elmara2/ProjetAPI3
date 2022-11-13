package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;

import java.util.List;

public interface InterfClasseService extends InterfService<Classe>{
    public List<Classe> read(String sigle);
    public Classe rechClasse(String sigle);
}
