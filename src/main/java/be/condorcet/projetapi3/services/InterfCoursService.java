package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfCoursService extends InterfService<Cours>{
    public List<Cours> read(String intitule);
    public Cours rechCours(String code);
    public List<Infos> readInfos(Cours cours) throws Exception;
}
