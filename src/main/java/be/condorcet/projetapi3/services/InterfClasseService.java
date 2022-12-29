package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Classe;
import be.condorcet.projetapi3.entities.Cours;
import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.Salle;

import java.util.List;

public interface InterfClasseService extends InterfService<Classe>{
    public List<Classe> read(String sigle);
    public Classe rechClasse(String sigle);
    public List<Infos> readInfos(Classe classe) throws Exception;

    /*public void addCoursForClasse(Classe classe, Cours cours);*/
}
