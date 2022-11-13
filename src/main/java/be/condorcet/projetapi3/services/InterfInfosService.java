package be.condorcet.projetapi3.services;

import be.condorcet.projetapi3.entities.Infos;
import be.condorcet.projetapi3.entities.InfosPK;

import java.util.List;

public interface InterfInfosService extends InterfService<Infos>{
    public List<Infos> read(Integer nbreheures);
}
