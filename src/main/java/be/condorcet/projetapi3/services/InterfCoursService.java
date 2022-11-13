package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;

import java.util.List;

public interface InterfCoursService extends InterfService<Cours>{
    public List<Cours> read(String intitule);
    public Cours rechCours(String code);
}
